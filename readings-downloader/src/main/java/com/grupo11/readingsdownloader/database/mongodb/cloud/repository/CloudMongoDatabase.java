package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.mongodb.client.FindIterable;
import org.bson.Document;

public interface CloudMongoDatabase {

    // Test query.
    FindIterable<Document> findOne();

}
