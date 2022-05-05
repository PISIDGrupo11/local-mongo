package com.grupo11.readingsprocessor.service.usecases;


import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.RawData;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FetchSensorsWithoutManufactureUseCase {

    @Value("${spring.data.mongodb.local.collections.no-manufacture-sensor-timestamp-holder}")
    private String noManufactureSensorTsHolder;

    @Value("${spring.data.mongodb.local.collections.sensors-without-manufacture-info}")
    private String sensorsWithoutManufactureInfoCollection;

    private final LocalMongoDBRepository localMongoDBRepository;


    public FetchSensorsWithoutManufactureUseCase(LocalMongoDBRepository localMongoDBRepository){
        this.localMongoDBRepository = localMongoDBRepository;
    }


    public RawData execute() throws NotFoundException {

        if(localMongoDBRepository.collectionIsEmpty(noManufactureSensorTsHolder)){
            return localMongoDBRepository.getBulkData(sensorsWithoutManufactureInfoCollection);
        }
        ObjectId lastSendId = localMongoDBRepository.getLastSentId(noManufactureSensorTsHolder);
        return localMongoDBRepository.getMostRecentData(lastSendId, sensorsWithoutManufactureInfoCollection);

    }
}
