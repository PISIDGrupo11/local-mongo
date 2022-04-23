package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class LocalMongoDatabaseImpl implements LocalMongoDatabase {

    private final MongoDatabase session;
    @Value("${spring.data.mongodb.local.collections.raw-data}")
    private String filteredDataCollection;
    @Value("${spring.data.mongodb.local.collections.cloudsql-backup-sensor}")
    private String cloudSQLBackupSensorCollection;
    @Value("${spring.data.mongodb.local.collections.cloudsql-backup-zone}")
    private String cloudSQLBackupZoneCollection;

    public LocalMongoDatabaseImpl(@Qualifier("local") MongoDatabase session) {
        this.session = session;
    }

    @Override
    public void insertNewRawData(List<Document> filteredData) {
        MongoCollection<Document> collection = session.getCollection(filteredDataCollection);
        collection.insertMany(filteredData);
    }

    @Override
    public void insertCloudBackupZone(List<Document> cloudBackupZone) {
        MongoCollection<Document> collection = session.getCollection(cloudSQLBackupZoneCollection);
        collection.insertMany(cloudBackupZone);
    }

    @Override
    public void insertCloudBackupSensor(List<Document> cloudBackupSensor) {
        MongoCollection<Document> collection = session.getCollection(cloudSQLBackupSensorCollection);
        collection.insertMany(cloudBackupSensor);
    }
}
