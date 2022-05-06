package com.grupo11.readingsprocessor.database;

import com.grupo11.readingsprocessor.database.models.BackUpSensorColumns;
import com.grupo11.readingsprocessor.database.models.RawDataColumns;
import com.mongodb.BasicDBObject;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.mongodb.client.model.Filters.eq;

@Component
public class LocalMongoDBImpl implements LocalMongoDB {

    private final MongoDatabase session;

    @Value("${spring.data.mongodb.local.collections.sensors-without-manufacture-info}")
    private String sensorsWithoutManufactureInfoCollection;
    @Value("${spring.data.mongodb.local.collections.no-manufacture-sensor-timestamp-holder}")
    private String noManufactureSensorTsHolder;

    @Value("${spring.data.mongodb.local.collections.sensor-manufactured-info}")
    private String manufacturingSensorDataCollection;

    public LocalMongoDBImpl(MongoDatabase session) {
        this.session = session;
    }

    @Override
    public FindIterable<Document> getMostRecentData(ObjectId objectId, String collectionName ,String zone) {
        MongoCollection<Document> collection = session.getCollection(collectionName);
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put(RawDataColumns.ID.getColumnIdentifier(),
                new BasicDBObject("$gt", objectId));
        gtQuery.put(RawDataColumns.ZONE.getColumnIdentifier(),
                new BasicDBObject("$eq", zone));
        return collection.find(gtQuery)
                .sort(new BasicDBObject(RawDataColumns.ID.getColumnIdentifier(), 1));
    }

    @Override
    public FindIterable<Document> getMostRecentNoManufactureSensorsData(ObjectId objectId) {
        MongoCollection<Document> collection = session
                .getCollection(sensorsWithoutManufactureInfoCollection);
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put(BackUpSensorColumns.ID.getColumnIdentifier(),
                new BasicDBObject("$gt", objectId));
        return collection.find(gtQuery).sort(new BasicDBObject("_id", 1));
    }

    @Override
    public FindIterable<Document> getLastSentId(String collectionName, String zone) {
        MongoCollection<Document> collection = session.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        query.put("Zona", new BasicDBObject("$eq", zone));
        return collection.find(query).sort(new BasicDBObject("_id", -1)).limit(1);
    }

    @Override
    public FindIterable<Document> getLastSentIdAnomalyTH(){
        MongoCollection<Document> collection = session.getCollection(noManufactureSensorTsHolder);
        BasicDBObject query = new BasicDBObject();
        return collection.find(query).sort(new BasicDBObject("_id", -1)).limit(1);
    }

    @Override
    public void updateLastSentSensorData(Document lastSentSensorData, String collectionName) {
        MongoCollection<Document> collection = session.getCollection(collectionName);
        collection.insertOne(lastSentSensorData);
    }

    @Override
    public void updateLastSentObjectIdOfNoManufactureSensorData(Document lastSentSensorData) {
        MongoCollection<Document> collection = session.getCollection(noManufactureSensorTsHolder);
        collection.insertOne(lastSentSensorData);
    }

    @Override
    public FindIterable<Document> getBulkData(String collectionName, String zone) {
        MongoCollection<Document> collection = session.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        query.put(RawDataColumns.ZONE.getColumnIdentifier(), new BasicDBObject("$eq", zone));
        return collection.find(query).sort(new BasicDBObject("_id", -1)).limit(10);
    }
    @Override
    public FindIterable<Document> getNoManufactureCollection(){
        MongoCollection<Document> collection = session.getCollection(
                sensorsWithoutManufactureInfoCollection);
        BasicDBObject query = new BasicDBObject();
        return collection.find(query).sort(
                new BasicDBObject(
                        BackUpSensorColumns.ID.getColumnIdentifier(), -1))
                .limit(10);

    }

    @Override
    public FindIterable<Document> getCollectionSize(String collection, String zone) {
        MongoCollection<Document> coll = session.getCollection(collection);
        BasicDBObject query = new BasicDBObject();
        query.put(RawDataColumns.ZONE.getColumnIdentifier(),
                new BasicDBObject("$eq", zone));
        return coll.find(query);
    }

    @Override
    public FindIterable<Document> getAnomalyTHCollectionSize() {
        MongoCollection<Document> coll = session.getCollection(noManufactureSensorTsHolder);
        BasicDBObject query = new BasicDBObject();
        return coll.find(query);
    }

    @Override
    public FindIterable<Document> getManufacturingData() {
        MongoCollection<Document> coll = session.getCollection(manufacturingSensorDataCollection);
        return coll.find();
    }

    public DistinctIterable<Integer> getZonesFromManufactureData(){
        MongoCollection<Document> coll = session.getCollection(manufacturingSensorDataCollection);
        return coll.distinct(BackUpSensorColumns.IDZONE.getColumnIdentifier(),
                Integer.class);

    }
}
