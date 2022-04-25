package com.grupo11.readingsdownloader.database.mongodb.cloud;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;

public interface CloudMongoDatabase {

    // Test query.
    FindIterable<Document> findOne();

    FindIterable<Document> getMostRecentData(ObjectId objectId);

    FindIterable<Document> getBulkData();

}
