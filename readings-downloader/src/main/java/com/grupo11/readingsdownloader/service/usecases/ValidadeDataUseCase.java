package com.grupo11.readingsdownloader.service.usecases;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;

import org.springframework.stereotype.Component;

import java.util.MissingResourceException;

@Component
public class ValidadeDataUseCase {

    private final MySQLCloudRepository mySQLCloudRepository;

    public ValidadeDataUseCase(MySQLCloudRepository mySQLCloudRepository) {
        this.mySQLCloudRepository = mySQLCloudRepository;
    }

    public void execute(CloudSensor cloudSensor) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
