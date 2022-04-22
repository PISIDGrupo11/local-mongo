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
    @Value("${spring.data.mongodb.local.collections.filtered-data}")
    private String filteredDataCollection;
    @Value("${spring.data.mongodb.local.collections.cloudsql-backup-sensor}")
    private String cloudSQLBackupSensorCollection;
    @Value("${spring.data.mongodb.local.collections.cloudsql-backup-zone}")
    private String cloudSQLBackupZoneCollection;

    public LocalMongoDatabaseImpl(@Qualifier("local") MongoDatabase session) {
        this.session = session;
    }

    @Override
    public void insertNewFilteredData(List<Document> filteredData) {
        MongoCollection<Document> collection = session.getCollection(filteredDataCollection);
        collection.insertMany(filteredData);
    }

    @Override
    public void insertCloudBackupZone(Document cloudBackupZone) {
        MongoCollection<Document> collection = session.getCollection(cloudSQLBackupZoneCollection);
        collection.insertOne(cloudBackupZone);
    }

    @Override
    public FindIterable<Document> getCloudBackupZone(int idZona) {
        MongoCollection<Document> collection = session.getCollection(cloudSQLBackupZoneCollection);
        BasicDBObject query = new BasicDBObject();
        query.put("idZona", idZona);
        return collection.find(query);
    }

    @Override
    public void insertCloudBackupSensor(Document cloudBackupSensor) {
        MongoCollection<Document> collection = session.getCollection(cloudSQLBackupSensorCollection);
        collection.insertOne(cloudBackupSensor);
    }

    @Override
    public FindIterable<Document> getCloudBackupSensor(int idSensor, String tipo) {
        MongoCollection<Document> collection = session.getCollection(cloudSQLBackupSensorCollection);
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject("idSensor", idSensor));
        obj.add(new BasicDBObject("tipo", tipo));
        query.put("$and", obj);
        return collection.find(query);
    }
}
