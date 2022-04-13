package com.grupo11.readingsprocessor.database;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;

public class LocalMongoDBImpl implements LocalMongoDB {
    @Override
    public FindIterable<Document> getMostRecentData(ObjectId objectId) {
        return null;
    }

    @Override
    public FindIterable<Document> getLastSentId() {
        return null;
    }
}
