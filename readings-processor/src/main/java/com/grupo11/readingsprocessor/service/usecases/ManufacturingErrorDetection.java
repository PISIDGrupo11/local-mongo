package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.FilterSensorData;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.models.SensorDataClassification;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;

@Component
public class ManufacturingErrorDetection {

    @Value("${broker.topic2}")
    private String MQTTANOMALYTOPIC;
    @Value("${broker.topic1}")
    private String MQTTMEASUREMENTTOPIC;


    private final LocalMongoDBRepository localMongoDBRepository;

    public ManufacturingErrorDetection(LocalMongoDBRepository localMongoDBRepository){
        this.localMongoDBRepository = localMongoDBRepository;
    }

    public FilterSensorData execute(SensorData sensorData){
        HashMap<String, Hashtable<String, Double>> mapManufactureSensorData = localMongoDBRepository.
                                                                                    getManufactureSensorInformation();

        if(sensorData.getMedicao() < mapManufactureSensorData.get(sensorData.getSensor()).get("LimiteSuperior")
            || sensorData.getMedicao() > mapManufactureSensorData.get(sensorData.getSensor()).get("LimiteInferior")){

            return new FilterSensorData(SensorDataClassification.ManufactureAnomaly,sensorData, MQTTANOMALYTOPIC);
        }
        else{
            return new FilterSensorData(SensorDataClassification.NormalMeasurement,sensorData,MQTTMEASUREMENTTOPIC);
        }
    }
}
