package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.exceptions.NotFoundException;
import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupZone;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocalMongoRepository {

    private final LocalMongoDatabase database;
    private final LocalMongoMapper mapper;

    public LocalMongoRepository(LocalMongoDatabase database, LocalMongoMapper mapper) {
        this.database = database;
        this.mapper = mapper;
    }

    public void insertNewRawData(List<CloudSensor> rawData) {
        database.insertNewRawData(rawData.stream()
                .map(mapper::mapFilteredDataToDocument).collect(Collectors.toList()));
    }

    public void insertCloudBackupZone(List<CloudSQLBackupZone> cloudSQLBackupZones) {
        database.insertCloudBackupZone(cloudSQLBackupZones.stream()
                .map(mapper::mapCloudBackupZoneToDocument).collect(Collectors.toList()));
    }

    public void insertCloudBackupSensor(List<CloudSQLBackupSensor> cloudBackupSensors) {
        database.insertCloudBackupSensor(cloudBackupSensors.stream()
                .map(mapper::mapCloudBackupSensorToDocument).collect(Collectors.toList()));
    }

}
