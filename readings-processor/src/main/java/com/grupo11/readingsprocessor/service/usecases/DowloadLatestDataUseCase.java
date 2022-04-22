package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DowloadLatestDataUseCase {

    private final LocalMongoDBRepository localMongoDBRepository;


    public List<SensorData> execute(ObjectId objectId) {

        return localMongoDBRepository.getMostRecentData(objectId);
    }
}