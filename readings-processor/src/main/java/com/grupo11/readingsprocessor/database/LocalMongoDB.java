package com.grupo11.readingsprocessor.database;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;

public interface LocalMongoDB {

    FindIterable<Document> getMostRecentData(ObjectId objectId);

    FindIterable<Document> getLastSentId();
}
