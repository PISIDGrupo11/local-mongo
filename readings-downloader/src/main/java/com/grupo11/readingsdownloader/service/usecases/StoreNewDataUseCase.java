package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreNewDataUseCase {

    private final LocalMongoRepository localMongoRepository;

    public StoreNewDataUseCase(LocalMongoRepository localMongoRepository) {
        this.localMongoRepository = localMongoRepository;
    }

    public void execute(List<CloudSensor> cloudSensors) {
        localMongoRepository.insertNewFilteredData(cloudSensors);
    }
}
