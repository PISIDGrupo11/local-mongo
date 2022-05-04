package com.grupo11.readingsprocessor.database.repository;

import com.grupo11.readingsprocessor.database.LocalMongoDB;
import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.RawData;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Hashtable;
@Repository
@AllArgsConstructor
public class LocalMongoDBRepository {

    private final LocalMongoDB database;
    private final LocalMongoDBMapper mapper;

    public RawData getMostRecentData(ObjectId objectId,
                                     String collectionName) {
        return mapper.mapMultipleDocumentsToSensorData(
                database.getMostRecentData(objectId, collectionName)
        );
    }

    public ObjectId getLastSentId(String collectionName) throws NotFoundException {
        MongoCursor<Document> cursor = database.getLastSentId(collectionName).iterator();
        if (!cursor.hasNext())
            throw new NotFoundException("No data has been sent yet!");
        return mapper.mapDocumentToObjectId(cursor.next());
    }

    public void updateLastSentObjectId(ObjectId lastSentSensorData) {
        database.updateLastSentSensorData(mapper.mapSensorObjectIdToDocument(lastSentSensorData));
    }

    public RawData getBulkData(String collectionName) {
        return mapper.mapMultipleDocumentsToSensorData(database.getBulkData(collectionName));
    }

    public boolean collectionIsEmpty(String collection) {
        FindIterable<Document> iterable = database.getCollectionSize(collection);
        return !iterable.iterator().hasNext();
    }

    public HashMap<String, Hashtable<String, Double>> getManufactureSensorInformation() {
        FindIterable<Document> iterable = database.getManufacturingData();
        return mapper.collectionToHashMap(iterable);
    }
}


