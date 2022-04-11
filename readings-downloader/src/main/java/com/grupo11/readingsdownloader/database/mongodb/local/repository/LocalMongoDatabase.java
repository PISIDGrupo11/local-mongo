package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.models.FilteredData;
import org.bson.Document;

import java.util.List;

public interface LocalMongoDatabase {

    void insertNewFilteredData(List<Document> filteredData);
}
