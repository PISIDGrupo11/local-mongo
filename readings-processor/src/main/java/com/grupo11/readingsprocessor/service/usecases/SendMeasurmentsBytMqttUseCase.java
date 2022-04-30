package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.FilterSensorData;
import com.grupo11.readingsprocessor.database.models.*;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.factory.ExponentialMovingAverageServiceFactory;
import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import com.grupo11.readingsprocessor.service.ExponentialMovingAverageService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;

@Component
public class SendMeasurmentsBytMqttUseCase {

    @Value("${broker.topic1}")
    private String readingsTopic;

    @Value("${broker.topic3}")
    private String wrongFormatTopic;

    private final MQTTSender mqttSender;
    private final MQTTMapper mapper;
    private final LocalMongoDBRepository repository;

    private final ManufacturingErrorDetection manufacturingErrorDetection;


    public SendMeasurmentsBytMqttUseCase(MQTTSender mqttSender, MQTTMapper mapper,
                                         LocalMongoDBRepository repository,
                                         ManufacturingErrorDetection manufacturingErrorDetection) {
        this.mqttSender = mqttSender;
        this.mapper = mapper;
        this.repository = repository;
        this.manufacturingErrorDetection = manufacturingErrorDetection;
    }

    public void execute(RawData measurements, HashMap<String, Hashtable<String, Double>> mapManufactureSensorData)
            throws MqttException {
        ExponentialMovingAverageServiceFactory emaServiceFactory
            = ExponentialMovingAverageServiceFactory.getInstance();

        for (SensorData sensorData: measurements.getSensorDataList()) {

            FilterSensorData filterSensorData = manufacturingErrorDetection.execute(sensorData, mapManufactureSensorData);
            if(filterSensorData.getClassification().equals(SensorDataClassification.NormalMeasurement)){

                sendMedicao(emaServiceFactory, filterSensorData);
            }
            else if(filterSensorData.getClassification().equals(SensorDataClassification.ManufactureAnomaly)){
                sendAnomaly(filterSensorData);
            }
        }
        for(UnprocessableEntity entity : measurements.getUnprocessableEntityList()) {
            sendUnprocessableEntity(entity);
        }
    }

    private void sendUnprocessableEntity(UnprocessableEntity entity) throws MqttException {
        System.out.println("Sending: " + entity);
        mqttSender.send(entity, wrongFormatTopic);
        repository.updateLastSentObjectId(entity.getObjectId());
    }

    private void sendMedicao(ExponentialMovingAverageServiceFactory emaServiceFactory,
                             FilterSensorData filterSensorData) throws MqttException {
        System.out.println("Sending: " + filterSensorData);
        Medicao reading = mapper.mapSensorDataToMedicao(filterSensorData.getSensorData());

        ExponentialMovingAverageService emaService
            = emaServiceFactory.getService(reading.getSensor());

        emaService.tryReset(reading.getLeitura());
        emaService.update(reading.getLeitura());
        reading.setLeitura(emaService.get());

        mqttSender.send(reading, filterSensorData.getMqttTopic());
        repository.updateLastSentObjectId(filterSensorData.getSensorData().getId());
    }

    private void sendAnomaly(FilterSensorData filterSensorData)  throws MqttException {
        System.out.println("Sending: " + filterSensorData);
        Anomalia reading = mapper.mapSensorDataToAnomalia(filterSensorData.getSensorData(),
                AnomalyType.SensorFailure.anomalyType);
        mqttSender.send(reading, filterSensorData.getMqttTopic());
        repository.updateLastSentObjectId(filterSensorData.getSensorData().getId());

    }
}
