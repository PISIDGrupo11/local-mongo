package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class CloudMongoMapper {

    // Test query.
    public Optional<Document> mapFindOne(FindIterable<Document> cursor) {
        return StreamSupport.stream(cursor.spliterator(), false).findFirst();
    }
}
