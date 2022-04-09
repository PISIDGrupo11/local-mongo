package com.grupo11.readingsdownloader.database.mongodb.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document
public class FilteredData {
    @MongoId
    private String id;
    private String zona;
    private String sensor;
    private LocalDateTime data;
    private float medicao;
    private String timestampMedicao;

    public FilteredData(String zona, String sensor, LocalDateTime data, float medicao, String timestampMedicao) {
        this.zona = zona;
        this.sensor = sensor;
        this.data = data;
        this.medicao = medicao;
        this.timestampMedicao = timestampMedicao;
    }
}
