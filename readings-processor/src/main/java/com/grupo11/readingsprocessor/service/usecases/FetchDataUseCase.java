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
    private String readingsProcessorTimestampHolderCollection;
    @Value("${spring.data.mongodb.local.collections.raw-data}")
    private String localMongoDataCollection;
    private final LocalMongoDBRepository mongoDBRepository;


    public FetchDataUseCase(LocalMongoDBRepository mongoDBRepository) {
        this.mongoDBRepository = mongoDBRepository;
    }

    public RawData execute(String zone) throws NotFoundException {
        if (mongoDBRepository.collectionIsEmpty(readingsProcessorTimestampHolderCollection, zone)) {
            return mongoDBRepository.getBulkData(localMongoDataCollection, zone);
        }
        ObjectId lastSentId = mongoDBRepository.getLastSentId(readingsProcessorTimestampHolderCollection, zone);
        return mongoDBRepository.getMostRecentData(lastSentId, localMongoDataCollection, zone);
    }
}
