package com.grupo11.readingsprocessor.database.repository;

import com.grupo11.readingsprocessor.database.LocalMongoDB;
import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.RawData;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Queue;
import java.util.Stack;

@Repository
@AllArgsConstructor
public class LocalMongoDBRepository {

    private final LocalMongoDB database;
    private final LocalMongoDBMapper mapper;

    public RawData getMostRecentData(ObjectId objectId,
                                     String collectionName, String zone) {
        return mapper.mapMultipleDocumentsToSensorData(
                database.getMostRecentData(objectId, collectionName, zone)
        );
    }


    public RawData getMostRecentNoManufactureSensorsData(ObjectId objectId){
        return mapper.mapMultipleDocumentsToSensorData(
                database.getMostRecentNoManufactureSensorsData(objectId));
    }

    public ObjectId getLastSentIdAnomalyTH() throws NotFoundException {
        MongoCursor<Document> cursor = database.getLastSentIdAnomalyTH().iterator();
        if (!cursor.hasNext())
            throw new NotFoundException("No data has been sent yet!");
        return mapper.mapDocumentToObjectId(cursor.next());
    }

    public RawData getNoManufactureCollection(){
        return mapper.mapMultipleDocumentsToSensorData(database.getNoManufactureCollection());
    }

    public ObjectId getLastSentId(String collectionName, String zone) throws NotFoundException {
        MongoCursor<Document> cursor = database.getLastSentId(collectionName, zone).iterator();
        if (!cursor.hasNext())
            throw new NotFoundException("No data has been sent yet!");
        return mapper.mapDocumentToObjectId(cursor.next());
    }

    public void updateLastSentObjectId(ObjectId lastSentSensorData, String collectionName, String zone) {
        database.updateLastSentSensorData(mapper.mapSensorObjectIdToDocument(lastSentSensorData, zone),
                collectionName);
    }

    public void updateLastSentObjectIdOfNoManufactureSensorData(ObjectId lastSentSensorData){
        database.updateLastSentObjectIdOfNoManufactureSensorData(
                mapper.mapSensorObjectIdOfNoManufactureSensorDataToDocument(lastSentSensorData));
    }

    public RawData getBulkData(String collectionName, String zone) {
        return mapper.mapMultipleDocumentsToSensorData(database.getBulkData(collectionName, zone));
    }

    public boolean collectionIsEmpty(String collection, String zone) {
        FindIterable<Document> iterable = database.getCollectionSize(collection, zone);
        return !iterable.iterator().hasNext();
    }

    public boolean AnomalyTHCollectionIsEmpty(){
        FindIterable<Document> iterable = database.getAnomalyTHCollectionSize();
        return !iterable.iterator().hasNext();
    }

    public HashMap<String, Hashtable<String, Double>> getManufactureSensorInformation() {
        FindIterable<Document> iterable = database.getManufacturingData();
        return mapper.collectionToHashMap(iterable);
    }

    public DistinctIterable<Integer> getZonesFromManufactureData(){
        return database.getZonesFromManufactureData();
    }
}


