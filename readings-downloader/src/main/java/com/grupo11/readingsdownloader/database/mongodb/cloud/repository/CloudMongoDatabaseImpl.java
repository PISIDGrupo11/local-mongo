package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

public class CloudMongoDatabaseImpl implements CloudMongoDatabase {

    private final MongoDatabase session;
    private final MongoCollection<Document> collection;

    public CloudMongoDatabaseImpl(@Qualifier("cloud") MongoDatabase session, MongoCollection<Document> collection) {
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

    @Override
    public FindIterable<Document> getMostRecentData(ObjectId objectId) {
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("_id", new BasicDBObject("$gt", objectId));
        return collection.find(gtQuery).sort(new BasicDBObject("_id", 1)).limit(100);
    }

    @Override
    public FindIterable<Document> getBulkData() {
        BasicDBObject query = new BasicDBObject();
        return collection.find(query).sort(new BasicDBObject("_id", 1)).limit(100);
    }
}
