package com.grupo11.readingsprocessor.database.repository;

import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocalMongoDBMapper {

    public List<SensorData> mapMultipleDocumentsToSensorData(FindIterable<Document> documents) {
        List<SensorData> dataList = new ArrayList<>();
        for (Document document : documents)
            dataList.add(new SensorData(
                    document.getObjectId("_id"),
                    document.getString("Data"),
                    Double.parseDouble(document.getString("Medicao")),
                    document.getString("Sensor"),
                    document.getString("Zona")
            ));
        return dataList;
    }

    public List<Medicao> mapMultipleDocumentsToMedicao(FindIterable<Document> documents){
        List<Medicao> dataList = new ArrayList<>();
        documents.map(x -> new Medicao( x.getInteger("Zona"),
                x.getString("Sensor"),
                Double.parseDouble(x.getString("Medicao")),
                x.getDate("Data")))
                .into(dataList);
        return dataList;
    }

    public ObjectId mapDocumentToObjectId(Document document) {
        return document.getObjectId("_id");
    }

    public Document mapSensorObjectIdToDocument(ObjectId objectId) {
        Document document = new Document();
        document.append("_id", objectId);
        return document;
    }
}
