package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Date;

public interface CloudMongoDatabase {

    // Test query.
    FindIterable<Document> findOne();

    FindIterable<Document> getMostRecentData(ObjectId objectId);

    FindIterable<Document> getBulkData();

}
