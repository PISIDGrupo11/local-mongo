package com.grupo11.readingsprocessor.database.models;

public enum RawDataColumns {

    ID("_id"),
    DATE("Data"),
    MEASUREMENT("Medicao"),
    SENSOR("Sensor"),
    ZONE("Zona");

    private String columnIdentifier;

    RawDataColumns(String value) {
        this.columnIdentifier = value;
    }

    public String getColumnIdentifier(){
        return this.columnIdentifier;
    }
}
