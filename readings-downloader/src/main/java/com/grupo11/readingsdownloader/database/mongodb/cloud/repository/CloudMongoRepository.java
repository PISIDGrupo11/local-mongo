package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CloudMongoRepository {

    private final CloudMongoDatabase database;
    private final CloudMongoMapper mapper;

    public CloudMongoRepository(@Qualifier("cloud") CloudMongoDatabase database, CloudMongoMapper mapper) {
        this.database = database;
        this.mapper = mapper;
    }

    // Test query
    public Optional<Document> findOne() {
        return mapper.mapFindOne(database.findOne());
    }
}
