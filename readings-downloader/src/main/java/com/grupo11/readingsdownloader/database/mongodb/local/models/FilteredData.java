package com.grupo11.readingsdownloader.database.mongodb.local.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document
public class FilteredData {
    @MongoId
    private ObjectId id;
    private String zona;
    private String sensor;
    private LocalDateTime data;
    private float medicao;
    private String timestampMedicao;

}
