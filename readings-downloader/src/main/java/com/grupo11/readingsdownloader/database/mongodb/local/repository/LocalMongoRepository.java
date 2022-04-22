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

    public void insertNewFilteredData(List<CloudSensor> filteredData) {
        database.insertNewFilteredData(filteredData.stream()
                .map(mapper::mapFilteredDataToDocument).collect(Collectors.toList()));
    }

    public void insertCloudBackupZone(CloudSQLBackupZone cloudSQLBackupZone) {
        database.insertCloudBackupZone(mapper.mapCloudBackupZoneToDocument(cloudSQLBackupZone));
    }

    public CloudSQLBackupZone getCloudBackupZone(int idZona) throws NotFoundException {
        MongoCursor<Document> cursor = database.getCloudBackupZone(idZona).cursor();
        if (!cursor.hasNext())
            throw new NotFoundException("CloudSQLBackupZone not found with id: " + idZona);
        return mapper.mapDocumentToCloudBackupZone(cursor.next());
    }

    public void insertCloudBackupSensor(CloudSQLBackupSensor cloudBackupSensor) {
        database.insertCloudBackupSensor(mapper.mapCloudBackupSensorToDocument(cloudBackupSensor));
    }

    public CloudSQLBackupSensor getCloudBackupSensor(int idSensor, String tipo) throws NotFoundException {
        MongoCursor<Document> cursor = database.getCloudBackupSensor(idSensor, tipo).cursor();
        if (!cursor.hasNext())
            throw new NotFoundException("CloudSQLBackupSensor not found with idSensor: " + idSensor +
                    ", tipo: " + tipo);
        return mapper.mapDocumentToCloudBackupSensor(cursor.next());
    }
}
