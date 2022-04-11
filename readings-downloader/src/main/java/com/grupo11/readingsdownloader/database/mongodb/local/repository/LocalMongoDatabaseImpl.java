package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.mongodb.client.MongoDatabase;

public class LocalMongoDatabaseImpl implements LocalMongoDatabase {

    private final MongoDatabase session;

    public LocalMongoDatabaseImpl(MongoDatabase session) {
        this.session = session;
    }
}
