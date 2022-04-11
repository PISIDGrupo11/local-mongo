package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.mongodb.local.models.FilteredData;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class LocalMongoMapper {

    public Document mapFilteredDataToDocument(FilteredData filteredData) {
        Document document = new Document();
        document.append("_id", filteredData.getId());
        document.append("zona", filteredData.getZona());
        document.append("sensor", filteredData.getSensor());
        document.append("data", filteredData.getData());
        document.append("medicao", filteredData.getMedicao());
        document.append("timestampMedicao", filteredData.getTimestampMedicao());
        return document;
    }
}
