package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DownloadReferenceValuesUseCase {

    private final MySQLCloudRepository mySQLCloudRepository;
    private final LocalMongoRepository localMongoRepository;

    public void execute() {
        localMongoRepository.insertCloudBackupSensor(mySQLCloudRepository.getSensors());
        localMongoRepository.insertCloudBackupZone(mySQLCloudRepository.getZonas());
    }

}
