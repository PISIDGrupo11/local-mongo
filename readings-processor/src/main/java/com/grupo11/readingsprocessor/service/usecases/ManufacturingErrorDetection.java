package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.FilterSensorData;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.models.SensorDataClassification;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;

@Component
public class ManufacturingErrorDetection {
    @Value("${broker.topic2}")
    private String MQTTANOMALYTOPIC;
    @Value("${broker.topic1}")
    private String MQTTMEASUREMENTTOPIC;

    private final LocalMongoDBRepository localMongoDBRepository;

    public ManufacturingErrorDetection(LocalMongoDBRepository localMongoDBRepository) {
        this.localMongoDBRepository = localMongoDBRepository;
    }

    public FilterSensorData execute(
        SensorData sensorData, HashMap<String, Hashtable<String, Double>> mapManufactureSensorData) {

        var sensorId = sensorData.getSensor().toLowerCase(Locale.ROOT);

        var isWithinFactoryBounds =
            sensorData.getMedicao() < mapManufactureSensorData.get(sensorId).get("LimiteInferior") ||
            sensorData.getMedicao() > mapManufactureSensorData.get(sensorId).get("LimiteSuperior");

        return new FilterSensorData(
            isWithinFactoryBounds ? SensorDataClassification.ManufactureAnomaly : SensorDataClassification.NormalMeasurement,
            sensorData,
            isWithinFactoryBounds ? MQTTANOMALYTOPIC : MQTTMEASUREMENTTOPIC
        );
    }
}