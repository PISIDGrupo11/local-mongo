package com.grupo11.readingsdownloader.database.mongodb.cloud.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "sensorh1")
public class CloudSensor {
    @MongoId
    private String _id;
    private String Data;
    private String Medicao;
    private String Sensor;
    private String Zona;
}
