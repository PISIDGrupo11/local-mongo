package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.CloudMongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class CloudMongoRepository {

    private final CloudMongoDatabase database;
    private final CloudMongoMapper mapper;

    public CloudMongoRepository(CloudMongoDatabase database, CloudMongoMapper mapper) {
        this.database = database;
        this.mapper = mapper;
    }

    // Test query
    public CloudSensor findOne() {
        Optional<Document> document = StreamSupport.stream(database.findOne().spliterator(), false)
                .findFirst();
        return mapper.mapFindOne(document.get());
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
