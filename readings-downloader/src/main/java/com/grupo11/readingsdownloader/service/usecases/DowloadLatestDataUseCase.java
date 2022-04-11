package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoDatabase;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DowloadLatestDataUseCase {

    private final CloudMongoDatabase cloudMongoDatabase;

    public DowloadLatestDataUseCase(CloudMongoDatabase cloudMongoDatabase) {
        this.cloudMongoDatabase = cloudMongoDatabase;
    }

    public List<CloudSensor> execute(LocalDateTime dateTime) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
