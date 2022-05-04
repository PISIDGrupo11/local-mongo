package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DownloadReferenceValuesUseCase {

    @Value("${spring.data.mongodb.local.collections.cloudsql-backup-sensor}")
    private String cloudsqlBackupSensorCollection;

    @Value("${spring.data.mongodb.local.collections.cloudsql-backup-zone}")
    private String cloudsqlBackupZoneCollection;

    private final MySQLCloudRepository mySQLCloudRepository;
    private final LocalMongoRepository localMongoRepository;

    public DownloadReferenceValuesUseCase(MySQLCloudRepository mySQLCloudRepository, LocalMongoRepository localMongoRepository) {
        this.mySQLCloudRepository = mySQLCloudRepository;
        this.localMongoRepository = localMongoRepository;
    }

    public List<CloudSQLBackupSensor> execute() {

        List<CloudSQLBackupSensor> cloudSQLBackupSensorList = mySQLCloudRepository.getSensors();
        if(localMongoRepository.collectionIsEmpty(cloudsqlBackupSensorCollection)) {
            localMongoRepository.insertCloudBackupSensor(cloudSQLBackupSensorList);
        }
        if(localMongoRepository.collectionIsEmpty(cloudsqlBackupZoneCollection)) {
            localMongoRepository.insertCloudBackupZone(mySQLCloudRepository.getZonas());
        }
        return cloudSQLBackupSensorList;
    }

}
