package com.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class FilteredData {

    private final ObjectId id;
    private final String data;
    private final double medicao;
    private final String sensor;
    private final String zona;

    @Override
    public String toString() {
        return "FilteredData{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", medicao='" + medicao + '\'' +
                ", sensor='" + sensor + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}
