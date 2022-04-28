package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.RawData;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchDataUseCase {

    @Value("${spring.data.mongodb.local.collections.readings-processor-timestamp-holder}")
    private String readingsTsHolder;
    private final LocalMongoDBRepository mongoDBRepository;

    public FetchDataUseCase(LocalMongoDBRepository mongoDBRepository) {
        this.mongoDBRepository = mongoDBRepository;
    }

    public RawData execute() throws NotFoundException {
        if (mongoDBRepository.collectionIsEmpty(readingsTsHolder)) {
            return mongoDBRepository.getBulkData();
        }
        ObjectId lastSentId = mongoDBRepository.getLastSentId();
        return mongoDBRepository.getMostRecentData(lastSentId);
    }

}
