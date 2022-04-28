package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;

@Component
public class FilterData {

    private LocalMongoDBRepository localMongoDBRepository;

    public FilterData(LocalMongoDBRepository localMongoDBRepository){
        this.localMongoDBRepository = localMongoDBRepository;
    }

    public void execute(SensorData sensorData){
        HashMap<String, Hashtable<String, Double>> mapManufactureSensorData = localMongoDBRepository.
                                                                                    getManufactureSensorInformation();



    }
}
