package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.List;

public interface LocalMongoDatabase {

    void insertNewFilteredData(List<Document> filteredData);

    void insertCloudBackupZone(Document cloudBackupZone);

    FindIterable<Document> getCloudBackupZone(int idZona);

    void insertCloudBackupSensor(Document cloudBackupSensor);

    FindIterable<Document> getCloudBackupSensor(int idSensor, String tipo);

}
