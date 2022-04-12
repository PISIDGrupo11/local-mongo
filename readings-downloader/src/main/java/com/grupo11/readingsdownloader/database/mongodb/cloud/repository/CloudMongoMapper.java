package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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

    public List<CloudSensor> mapMultipleDocumentsToCloudSensor(FindIterable<Document> documents) {
        List<CloudSensor> dataList = new ArrayList<>();
        for(Document document: documents)
            dataList.add(new CloudSensor(
                    document.getObjectId("_id"),
                    document.getString("Data"),
                    Double.parseDouble(document.getString("Medicao")),
                    document.getString("Sensor"),
                    document.getString("Zona")
            ));
        return dataList;
    }
}
