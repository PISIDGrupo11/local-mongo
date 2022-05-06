package com.grupo11.readingsprocessor.service.usecases;


import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.RawData;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class FetchSensorsWithoutManufactureUseCase {



    private final LocalMongoDBRepository localMongoDBRepository;


    public FetchSensorsWithoutManufactureUseCase(LocalMongoDBRepository localMongoDBRepository){
        this.localMongoDBRepository = localMongoDBRepository;
    }


    public RawData execute() throws NotFoundException {

        if(localMongoDBRepository.AnomalyTHCollectionIsEmpty()){
            return localMongoDBRepository.getNoManufactureCollection();
        }
        ObjectId lastSendId = localMongoDBRepository.getLastSentIdAnomalyTH();
        return localMongoDBRepository
                .getMostRecentNoManufactureSensorsData(lastSendId);

    }
}
