package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.FilterSensorData;
import com.grupo11.readingsprocessor.database.models.*;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.factory.ExponentialMovingAverageServiceFactory;
import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import com.grupo11.readingsprocessor.service.ExponentialMovingAverageService;
import com.grupo11.readingsprocessor.service.ReadingsClassifierService;
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
    private final ReadingsClassifierService readingsClassifierService;


    public SendMeasurmentsBytMqttUseCase(
        MQTTSender mqttSender,
        MQTTMapper mapper,
        LocalMongoDBRepository repository,
        ReadingsClassifierService readingsClassifierService
    ) {

        this.mqttSender = mqttSender;
        this.mapper = mapper;
        this.repository = repository;
        this.readingsClassifierService = readingsClassifierService;
    }

    public void execute(
        RawData measurements,
        HashMap<String, Hashtable<String, Double>> mapManufactureSensorData) throws MqttException {

        var emaServiceFactory = ExponentialMovingAverageServiceFactory.getInstance();

        // Process successfully parsed readings
        for (SensorData sensorData: measurements.getSensorDataList()) {
            var readingClassified = readingsClassifierService.getClassifiedReading(
                sensorData, mapManufactureSensorData);

            if (readingClassified.getClassification().equals(SensorDataClassification.NormalMeasurement)) {
                sendMedicao(emaServiceFactory, readingClassified);
                return;
            }

            sendAnomaly(readingClassified);
        }

        // Process parsing errors (unprocessable entities)
        for(UnprocessableEntity entity : measurements.getUnprocessableEntityList()) {
            sendUnprocessableEntity(entity);
        }
    }

    private void sendUnprocessableEntity(UnprocessableEntity entity) throws MqttException {
        System.out.println("Sending: " + entity);
        mqttSender.send(entity, wrongFormatTopic);
        repository.updateLastSentObjectId(entity.getObjectId());
    }

    private void sendMedicao(
        ExponentialMovingAverageServiceFactory emaServiceFactory,
        FilterSensorData filterSensorData
    ) throws MqttException {

        var reading = mapper.mapSensorDataToMedicao(filterSensorData.getSensorData());

        ExponentialMovingAverageService emaService
            = emaServiceFactory.getService(reading.getSensor());

        emaService.tryReset(reading.getLeitura());
        emaService.update(reading.getLeitura());
        reading.setLeitura(emaService.get());

        mqttSender.send(reading, filterSensorData.getMqttTopic());
        repository.updateLastSentObjectId(filterSensorData.getSensorData().getId());
    }

    private void sendAnomaly(FilterSensorData filterSensorData) throws MqttException {
        Anomalia reading = mapper.mapSensorDataToAnomalia(
            filterSensorData.getSensorData(), AnomalyType.SensorFailure.anomalyType);

        mqttSender.send(reading, filterSensorData.getMqttTopic());
        repository.updateLastSentObjectId(filterSensorData.getSensorData().getId());
    }
}

