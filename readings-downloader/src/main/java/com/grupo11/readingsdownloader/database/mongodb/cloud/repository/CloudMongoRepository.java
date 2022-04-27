package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.CloudMongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CloudMongoRepository {

    private final CloudMongoDatabase database;

    public CloudMongoRepository(CloudMongoDatabase database) {
        this.database = database;
    }

    public List<Document> getMostRecentData(ObjectId objectId) {
        List<Document> documents = new ArrayList<>();
        database.getMostRecentData(objectId).forEach(obj -> documents.add(obj));
        return documents;
    }

    public List<Document> getBulkData() {
        List<Document> documents = new ArrayList<>();
        database.getBulkData().forEach(obj -> documents.add(obj));
        return documents;
    }

}
