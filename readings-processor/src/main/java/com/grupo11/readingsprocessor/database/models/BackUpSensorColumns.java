package com.grupo11.readingsprocessor.database.models;

public enum BackUpSensorColumns {

    ID("_id"),
    IDSENSOR("idSensor"),
    IDZONE("idZona"),
    INFERIORLIMIT("limiteInferior"),
    UPPERLIMIT("limiteSuperior"),
    type("tipo");

    private final String columnIdentifier;
    BackUpSensorColumns(String value) {
        this.columnIdentifier = value;
    }

    public String getColumnIdentifier(){
        return this.columnIdentifier;
    }
}
