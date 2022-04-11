package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Qualifier;

public class LocalMongoDatabaseImpl implements LocalMongoDatabase {

    private final MongoDatabase session;

    public LocalMongoDatabaseImpl(@Qualifier("local") MongoDatabase session) {
        this.session = session;
    }
}
