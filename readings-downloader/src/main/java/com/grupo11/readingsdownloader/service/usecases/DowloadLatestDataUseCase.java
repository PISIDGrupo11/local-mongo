package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DowloadLatestDataUseCase {

    private final CloudMongoRepository cloudMongoRepository;
    private final LocalMongoRepository localMongoRepository;

    public DowloadLatestDataUseCase(CloudMongoRepository cloudMongoRepository,
                                    LocalMongoRepository localMongoRepository) {
        this.cloudMongoRepository = cloudMongoRepository;
        this.localMongoRepository = localMongoRepository;
    }

    public void execute() {
        Optional<ObjectId> mostRecentId = localMongoRepository.getMostRecentObjectId();
        if(mostRecentId.isPresent()) {
            localMongoRepository.insertNewRawData(cloudMongoRepository.getMostRecentData(mostRecentId.get()));
        }
        else {
            localMongoRepository.insertNewRawData(cloudMongoRepository.getBulkData());
        }
    }
}
