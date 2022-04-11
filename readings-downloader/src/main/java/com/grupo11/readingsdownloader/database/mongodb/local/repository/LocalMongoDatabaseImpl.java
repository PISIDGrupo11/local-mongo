package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.mongodb.local.models.FilteredData;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class LocalMongoDatabaseImpl implements LocalMongoDatabase {

    @Value("${spring.data.mongodb.local.collections.filtered-data}")
    private String filteredDataCollection;

    private final MongoDatabase session;

    public LocalMongoDatabaseImpl(@Qualifier("local") MongoDatabase session) {
        this.session = session;
    }

    @Override
    public void insertNewFilteredData(List<Document> filteredData) {
        MongoCollection<Document> collection = session.getCollection(filteredDataCollection);
        collection.insertMany(filteredData);
    }
}
