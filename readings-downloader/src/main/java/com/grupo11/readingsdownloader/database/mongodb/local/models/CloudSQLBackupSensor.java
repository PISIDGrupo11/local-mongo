package com.grupo11.readingsdownloader.database.mongodb.local.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class CloudSQLBackupSensor {
    @MongoId
    private String id;
    private float temperatura;
    private float humidade;
    private float luz;

    public CloudSQLBackupSensor(float temperatura, float humidade, float luz) {
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.luz = luz;
    }
}
