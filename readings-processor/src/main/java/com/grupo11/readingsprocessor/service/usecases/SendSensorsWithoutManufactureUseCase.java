package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.Sender;
import com.grupo11.readingsprocessor.database.models.*;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.mqtt.Topics;
import com.grupo11.readingsprocessor.service.ReadingsClassifierService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendSensorsWithoutManufactureUseCase {

    @Value("${spring.data.mongodb.local.collections.no-manufacture-sensor-timestamp-holder}")
    private String noManufactureSensorTsHolder;

    private final Sender sender;
    private final MQTTMapper mapper;
    private final LocalMongoDBRepository repository;

    public SendSensorsWithoutManufactureUseCase(Sender sender,
                                                MQTTMapper mapper,
                                                LocalMongoDBRepository repository) {
        this.sender = sender;
        this.mapper = mapper;
        this.repository = repository;
    }


    private void sendAnomaly(SensorData sensorData) throws MqttException {
        Anomalia reading = mapper.mapSensorDataToAnomalia(sensorData, AnomalyType.NoManufactureData.toString());
        sender.send(reading, Topics.Anomaly);
        repository.updateLastSentObjectId(sensorData.getId(), noManufactureSensorTsHolder);
    }

    private void sendUnprocessableEntity(UnprocessableEntity entity) throws MqttException {
        sender.send(entity, Topics.WrongFormat);
        repository.updateLastSentObjectId(entity.getObjectId(), noManufactureSensorTsHolder);
    }

    public void execute(RawData rawData) throws MqttException {

        for (SensorData sensorData : rawData.getSensorDataList()){
            sendAnomaly(sensorData);
        }
        for(UnprocessableEntity unprocessableEntity : rawData.getUnprocessableEntityList()){
            sendUnprocessableEntity(unprocessableEntity);

        }
    }
}
