package com.grupo11.readingsprocessor.database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalMongoDBImpl implements LocalMongoDB {

    private final MongoDatabase session;

    @Value("${spring.data.mongodb.local.collections.sensor-manufactured-info}")
    private String manufacturingSensorDataCollection;

    public LocalMongoDBImpl(MongoDatabase session) {
        this.session = session;
    }

    @Override
    public FindIterable<Document> getMostRecentData(ObjectId objectId, String collectionName) {
        MongoCollection<Document> collection = session.getCollection(collectionName);
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("_id", new BasicDBObject("$gt", objectId));
        return collection.find(gtQuery).sort(new BasicDBObject("_id", 1));
    }

    @Override
    public FindIterable<Document> getLastSentId(String collectionName) {
        MongoCollection<Document> collection = session.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        return collection.find(query).sort(new BasicDBObject("_id", -1)).limit(1);
    }

    @Override
    public void updateLastSentSensorData(Document lastSentSensorData) {
        MongoCollection<Document> collection = session.getCollection(readingsProcessorTimestampHolderCollection);
        collection.insertOne(lastSentSensorData);
    }

    @Override
    public FindIterable<Document> getBulkData(String collectionName) {
        BasicDBObject query = new BasicDBObject();
        MongoCollection<Document> collection = session.getCollection(collectionName);
        return collection.find(query).sort(new BasicDBObject("_id", -1)).limit(10);
    }

    @Override
    public FindIterable<Document> getCollectionSize(String collection) {
        MongoCollection<Document> coll = session.getCollection(collection);
        BasicDBObject query = new BasicDBObject();
        return coll.find(query);
    }

    @Override
    public FindIterable<Document> getManufacturingData() {
        MongoCollection<Document> coll = session.getCollection(manufacturingSensorDataCollection);
        return coll.find();
    }
}
