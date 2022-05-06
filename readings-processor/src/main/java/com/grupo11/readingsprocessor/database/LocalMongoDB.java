package com.grupo11.readingsprocessor.database;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;

public interface LocalMongoDB {

    FindIterable<Document> getMostRecentData(ObjectId objectId, String collectionName, String zone);

    FindIterable<Document> getMostRecentNoManufactureSensorsData(ObjectId objectId);
    FindIterable<Document> getLastSentId(String collectionName, String zone);

    FindIterable<Document> getLastSentIdAnomalyTH();

    public void updateLastSentSensorData(Document lastSentSensorData, String collectionName);

    public void updateLastSentObjectIdOfNoManufactureSensorData(Document lastSentSensorData);
    FindIterable<Document> getBulkData(String collectionName, String zone);

    FindIterable<Document> getNoManufactureCollection();

    FindIterable<Document> getCollectionSize(String collection, String zone);

    FindIterable<Document> getAnomalyTHCollectionSize();

    DistinctIterable<Integer> getZonesFromManufactureData();

    FindIterable<Document> getManufacturingData();
}
