package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.models.RawData;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DowloadLatestDataUseCase {


    private final LocalMongoRepository localMongoRepository;

    private final MySQLCloudRepository mySQLCloudRepository;

    public DowloadLatestDataUseCase(LocalMongoRepository localMongoRepository,
                                    MySQLCloudRepository mySQLCloudRepository) {
        this.localMongoRepository = localMongoRepository;
        this.mySQLCloudRepository = mySQLCloudRepository;
    }

    public void execute(RawData rawData,
                        List<CloudSQLBackupSensor> cloudSQLBackupSensorList) {

        localMongoRepository.insertNewRawData(rawData.getManufactureControlPassed());
        if(!rawData.getManufactureControlFail().isEmpty()){

            cloudSQLBackupSensorList = mySQLCloudRepository.getSensors();
            localMongoRepository.insertCloudBackupSensor(cloudSQLBackupSensorList);
            localMongoRepository.insertNewAnomalyData(rawData.getManufactureControlFail());

        }


    }
}
