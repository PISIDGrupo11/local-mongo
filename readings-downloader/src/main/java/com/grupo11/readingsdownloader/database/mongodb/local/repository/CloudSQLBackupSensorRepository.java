package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupSensor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CloudSQLBackupSensorRepository extends MongoRepository<CloudSQLBackupSensor, String> {

}
