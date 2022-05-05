package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.database.models.*;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.factory.ExponentialMovingAverageServiceFactory;
import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.Sender;
import com.grupo11.readingsprocessor.mqtt.Topics;
import com.grupo11.readingsprocessor.service.ExponentialMovingAverageService;
import com.grupo11.readingsprocessor.service.ReadingsClassifierService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;

@Component
public class SendMeasurmentsUseCase {

    @Value("${spring.data.mongodb.local.collections.readings-processor-timestamp-holder}")
    private String readingsProcessorTimestampHolderCollection;
    private final Sender sender;
    private final MQTTMapper mapper;
    private final LocalMongoDBRepository repository;
    private final ReadingsClassifierService readingsClassifierService;


    public SendMeasurmentsUseCase(Sender sender,
        MQTTMapper mapper,
        LocalMongoDBRepository repository,
        ReadingsClassifierService readingsClassifierService
    ) {

        this.sender = sender;
        this.mapper = mapper;
        this.repository = repository;
        this.readingsClassifierService = readingsClassifierService;
    }

    public void execute(
                        RawData measurements,
                        HashMap<String, Hashtable<String, Double>> mapManufactureSensorData
    ) throws MqttException {

        var emaServiceFactory = ExponentialMovingAverageServiceFactory.getInstance();

        // Process successfully parsed readings
        for (SensorData sensorData: measurements.getSensorDataList()) {
            var readingClassified = readingsClassifierService.getClassifiedReading(
                sensorData, mapManufactureSensorData);

            if (readingClassified.getClassification().equals(SensorDataClassification.NormalMeasurement)) {
                sendMedicao(emaServiceFactory, readingClassified);
                continue;
            }

            sendAnomaly(readingClassified);
        }

        // Process parsing errors (unprocessable entities)
        for(UnprocessableEntity entity : measurements.getUnprocessableEntityList()) {
            System.out.println(entity);
            sendUnprocessableEntity(entity);
        }
    }

    private void sendUnprocessableEntity(UnprocessableEntity entity) throws MqttException {
        sender.send(entity, Topics.WrongFormat);
        repository.updateLastSentObjectId(entity.getObjectId(),
                readingsProcessorTimestampHolderCollection);
    }

    private void sendMedicao(
        ExponentialMovingAverageServiceFactory emaServiceFactory,
        RawData.FilterSensorData filterSensorData
    ) throws MqttException {

        var reading = mapper.mapSensorDataToMedicao(filterSensorData.getSensorData());

        ExponentialMovingAverageService emaService
            = emaServiceFactory.getService(reading.getSensor());

        emaService.tryReset(reading.getLeitura());
        emaService.update(reading.getLeitura());
        reading.setLeitura(emaService.get());

        sender.send(reading, filterSensorData.getMqttTopic());
        repository.updateLastSentObjectId(filterSensorData.getSensorData().getId(),
                readingsProcessorTimestampHolderCollection);
    }

    private void sendAnomaly(RawData.FilterSensorData filterSensorData) throws MqttException {
        var anomalyType = filterSensorData.getClassification().equals(SensorDataClassification.ManufactureAnomaly)
            ? AnomalyType.SensorFailure
            : AnomalyType.SporadicEvent;

        Anomalia reading = mapper.mapSensorDataToAnomalia(
            filterSensorData.getSensorData(), anomalyType.toString());

        sender.send(reading, filterSensorData.getMqttTopic());
        repository.updateLastSentObjectId(filterSensorData.getSensorData().getId(),
                readingsProcessorTimestampHolderCollection);
    }
}

