package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CloudMongoDatabaseImpl implements CloudMongoDatabase {

    private final MongoDatabase session;
    private final MongoCollection<Document> collection;

    public CloudMongoDatabaseImpl(MongoDatabase session, MongoCollection<Document> collection) {
        this.session = session;
        this.collection = collection;
    }

    // Test query.
    @Override
    public FindIterable<Document> findOne() {
        BasicDBObject query = new BasicDBObject();
        query.put("Medicao", "24.61639494871795");
        return collection.find(query);
    }
}
