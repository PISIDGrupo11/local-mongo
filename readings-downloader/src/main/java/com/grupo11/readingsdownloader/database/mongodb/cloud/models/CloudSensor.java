package com.grupo11.readingsdownloader.database.mongodb.cloud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@Document(collection = "sensorh1")
public class CloudSensor {
    @Id
    @Field("_id")
    private final ObjectId id;

    @Field("Data")
    private final String data;

    @Field("Medicao")
    private final double medicao;

    @Field("Sensor")
    private final String sensor;

    @Field("Zona")
    private final String zona;

    @Override
    public String toString() {
        return "CloudSensor{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", medicao='" + medicao + '\'' +
                ", sensor='" + sensor + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}
