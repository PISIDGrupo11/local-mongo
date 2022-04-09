package com.grupo11.readingsdownloader.database.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.grupo11.readingsdownloader.database.mongodb.models.CloudSQLBackupSensor;

public interface CloudSQLBackupSensorRepository extends MongoRepository<CloudSQLBackupSensor, String> {

}
