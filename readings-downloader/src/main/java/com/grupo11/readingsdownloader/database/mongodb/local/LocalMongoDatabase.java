package com.grupo11.readingsdownloader.database.mongodb.local;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public interface LocalMongoDatabase {

    void insertNewRawData(List<Document> filteredData);

    void insertCloudBackupZone(List<Document> cloudBackupZone);

    void insertCloudBackupSensor(List<Document> cloudBackupSensor);

    FindIterable<Document> getCollectionSize(String collection);

    FindIterable<Document> getMostRecentObjectId(String collection);



}
