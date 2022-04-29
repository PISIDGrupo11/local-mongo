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

    public void execute(RawData measurements) throws MqttException {
        ExponentialMovingAverageServiceFactory emaServiceFactory
            = ExponentialMovingAverageServiceFactory.getInstance();

        for (SensorData sensorData: measurements.getSensorDataList()) {
            System.out.println("Sending: " + sensorData);

            FilterSensorData filterSensorData = manufacturingErrorDetection.execute(sensorData);
            if(filterSensorData.getClassification().compareTo(SensorDataClassification.NormalMeasurement) > 0){
                Medicao reading = mapper.mapSensorDataToMedicao(filterSensorData.getSensorData());
                ExponentialMovingAverageService emaService
                        = emaServiceFactory.getService(reading.getSensor());

                emaService.tryReset(reading.getLeitura());
                emaService.update(reading.getLeitura());
                reading.setLeitura(emaService.get());

                mqttSender.send(reading, readingsTopic);
                repository.updateLastSentObjectId(sensorData.getId());
            }

        }
        for(UnprocessableEntity entity : measurements.getUnprocessableEntityList()) {
            System.out.println("Sending: " + entity);
            mqttSender.send(entity, wrongFormatTopic);
            repository.updateLastSentObjectId(entity.getObjectId());
        }
    }
}
