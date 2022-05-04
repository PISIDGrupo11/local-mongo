package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.models.RawData;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SeparateDataToCategoriesUseCase {

    private  final LocalMongoRepository localMongoRepository;
    private final CloudMongoRepository cloudMongoRepository;



    public RawData execute(
            List<CloudSQLBackupSensor> cloudSQLBackupSensorList
    ){

        Optional<ObjectId> mostRecentId = localMongoRepository.getMostRecentObjectId();
        if(mostRecentId.isPresent()) {
            return localMongoRepository
                    .mapMultipleDocumentsToSensorData(
                            cloudMongoRepository.getMostRecentData(mostRecentId.get()),
                            cloudSQLBackupSensorList);
        }
        else{
            return localMongoRepository
                    .mapMultipleDocumentsToSensorData(
                            cloudMongoRepository.getBulkData(),
                            cloudSQLBackupSensorList);
        }

    }
}
