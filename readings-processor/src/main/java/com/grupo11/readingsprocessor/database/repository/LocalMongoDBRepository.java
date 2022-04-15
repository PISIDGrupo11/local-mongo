package com.grupo11.readingsprocessor.database.repository;

import com.grupo11.readingsprocessor.database.LocalMongoDB;
import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LocalMongoDBRepository {

    private final LocalMongoDB database;
    private final LocalMongoDBMapper mapper;

    public List<SensorData> getMostRecentData(ObjectId objectId) {
        return mapper.mapMultipleDocumentsToSensorData(database.getMostRecentData(objectId));
    }

    public ObjectId getLastSentId() throws NotFoundException {
        MongoCursor<Document> cursor = database.getLastSentId().iterator();
        if(!cursor.hasNext())
            throw new NotFoundException("No data has been sent yet!");
        return mapper.mapDocumentToObjectId(cursor.next());
    }

    public void updateLastSentSensorData(ObjectId lastSentSensorData) {
        database.updateLastSentSensorData(mapper.mapSensorObjectIdToDocument(lastSentSensorData));
    }
}
