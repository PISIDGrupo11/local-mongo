package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import java.util.Date;
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

    public List<CloudSensor> getMostRecentData(ObjectId objectId) {
        return mapper.mapGetMostRecentData(database.getMostRecentData(objectId));
    }

}