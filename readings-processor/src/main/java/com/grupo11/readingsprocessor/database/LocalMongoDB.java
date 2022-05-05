package com.grupo11.readingsprocessor.database;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.awt.*;

public interface LocalMongoDB {

    FindIterable<Document> getMostRecentData(ObjectId objectId, String collectionName);

    FindIterable<Document> getLastSentId(String collectionName);

    public void updateLastSentSensorData(Document lastSentSensorData, String collectionName);

    FindIterable<Document> getBulkData(String collectionName);

    FindIterable<Document> getCollectionSize(String collection);

    FindIterable<Document> getManufacturingData();
}
