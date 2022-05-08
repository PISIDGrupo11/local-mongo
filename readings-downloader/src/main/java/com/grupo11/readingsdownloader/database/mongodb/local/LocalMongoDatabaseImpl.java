package com.grupo11.readingsdownloader.database.mongodb.local;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class LocalMongoDatabaseImpl implements LocalMongoDatabase {

    private final MongoDatabase session;
    @Value("${spring.data.mongodb.local.collections.sensors-without-manufacture-info}")
    private String anomaliesDataCollections;

    @Value("${spring.data.mongodb.local.collections.rawdata}")
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

    public void insertNewAnomalyData(List<Document> anomalyData){
        MongoCollection<Document> collection = session.getCollection(anomaliesDataCollections);
        collection.insertMany(anomalyData);
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

    @Override
    public FindIterable<Document> getCollectionSize(String collection) {
        MongoCollection<Document> coll = session.getCollection(collection);
        BasicDBObject query = new BasicDBObject();
        return coll.find(query);
    }

    @Override
    public FindIterable<Document> getMostRecentObjectId(String collectionName) {
        MongoCollection<Document> collection = session.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        return collection.find(query).sort(new BasicDBObject("_id", -1)).limit(1);
    }
}
