package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoDatabase;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DowloadLatestDataUseCase {

    private final CloudMongoRepository cloudMongoRepository;

    public DowloadLatestDataUseCase(CloudMongoRepository cloudMongoRepository) {
        this.cloudMongoRepository = cloudMongoRepository;
    }

    public List<CloudSensor> execute(LocalDateTime dateTime) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
