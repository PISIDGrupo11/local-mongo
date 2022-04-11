package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class CloudMongoMapper {

    // Test query.
    public CloudSensor mapFindOne(Document document) {
        return new CloudSensor(
                document.getObjectId("_id"),
                document.getString("Data"),
                Double.parseDouble(document.getString("Medicao")),
                document.getString("Sensor"),
                document.getString("Zona")
        );
    }
}
