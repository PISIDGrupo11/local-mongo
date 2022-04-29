package com.grupo11.readingsprocessor.database.repository;

import com.grupo11.readingsprocessor.database.models.RawData;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.models.UnprocessableEntity;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LocalMongoDBMapper {

    public RawData mapMultipleDocumentsToSensorData(FindIterable<Document> documents) {
        List<SensorData> dataList = new ArrayList<>();
        List<UnprocessableEntity> unprocessableEntityList = new ArrayList<>();
        for (Document document : documents) {
            try {
                dataList.add(new SensorData(document.getObjectId("_id"), document.getString("Data"),
                        Double.parseDouble(document.getString("Medicao")), document.getString("Sensor"),
                        document.getString("Zona")));
            } catch (Exception e) {
                unprocessableEntityList.add(new UnprocessableEntity(document.getObjectId("_id"), document.toJson()));
            }
        }
        return new RawData(dataList, unprocessableEntityList);
    }

    public ObjectId mapDocumentToObjectId(Document document) {
        return document.getObjectId("_id");
    }

    public Document mapSensorObjectIdToDocument(ObjectId objectId) {
        Document document = new Document();
        document.append("_id", objectId);
        return document;
    }

    public HashMap<String, Hashtable<String, Double>> collectionToHashMap(Iterable<Document> documents) {
        HashMap<String, Hashtable<String, Double>> hashMap = new HashMap<>();
        for (Document document : documents) {
            String key = document.getString("tipo").toLowerCase(Locale.ROOT) + document.getString("idSensor");
            Hashtable<String, Double> limits = new Hashtable<String, Double>();
            limits.put("LimiteInferior", Double.parseDouble(document.getString("limiteInferior")));
            limits.put("LimiteSuperior", Double.parseDouble(document.getString("limiteSuperior")));
            hashMap.put(key, limits);
        }
        return hashMap;
    }
}
