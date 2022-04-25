package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartDownloadUseCase {

    private final CloudMongoRepository cloudMongoRepository;

    public StartDownloadUseCase(CloudMongoRepository cloudMongoRepository) {
        this.cloudMongoRepository = cloudMongoRepository;
    }

    public List<CloudSensor> execute() {
        return cloudMongoRepository.getBulkData();
    }
}
