package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.models.CloudSQLBackupZone;
import com.grupo11.readingsdownloader.database.mongodb.local.LocalMongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Repository
public class LocalMongoRepository {

    @Value("${spring.data.mongodb.local.collections.anomalies}")
    private String anomaliesDataCollections;

    @Value("${spring.data.mongodb.local.collections.rawdata}")
    private String filteredDataCollection;


    private final LocalMongoDatabase database;
    private final LocalMongoMapper mapper;

    public LocalMongoRepository(LocalMongoDatabase database, LocalMongoMapper mapper) {
        this.database = database;
        this.mapper = mapper;
    }

    public void insertNewRawData(List<Document> rawData) {
        database.insertNewRawData(rawData);
    }

    public void insertCloudBackupZone(List<CloudSQLBackupZone> cloudSQLBackupZones) {
        database.insertCloudBackupZone(cloudSQLBackupZones.stream()
                .map(mapper::mapCloudBackupZoneToDocument).collect(Collectors.toList()));
    }

    public void insertCloudBackupSensor(List<CloudSQLBackupSensor> cloudBackupSensors) {
        database.insertCloudBackupSensor(cloudBackupSensors.stream()
                .map(mapper::mapCloudBackupSensorToDocument).collect(Collectors.toList()));
    }

    public Optional<ObjectId> getMostRecentObjectId() {
        Optional<ObjectId> objectIdFromFilterCollection = Optional
                .ofNullable(database.getMostRecentObjectId(filteredDataCollection)
                .first())
                .flatMap(document -> mapper.mapDocumentToObjectId(document));
        Optional<ObjectId> objectIdFromAnomalyCollection = Optional
                .ofNullable(database.getMostRecentObjectId(filteredDataCollection)
                .first())
                .flatMap(document -> mapper.mapDocumentToObjectId(document));

        return getLaterObjectId(objectIdFromFilterCollection,objectIdFromAnomalyCollection);

    }

    private Optional<ObjectId> getLaterObjectId(Optional<ObjectId> objectId, Optional<ObjectId> objectId1){

        return objectId.isEmpty() ? objectId1 : objectId1.isEmpty() ? objectId :
                objectId.get().compareTo(objectId1.get()) > 0 ? objectId :
                        objectId1;
    }

    public boolean collectionIsEmpty(String collection) {
        FindIterable<Document> iterable = database.getCollectionSize(collection);
        return !iterable.iterator().hasNext();
    }

}
