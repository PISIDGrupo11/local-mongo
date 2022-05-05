package com.grupo11.readingsprocessor.database.models;

public enum AnomalyType {

    SensorFailure("Avaria do Sensor"),
    SporadicEvent("Eventos Esporádicos"),
    InvalidValue("Valor Inválido"),
    NoManufactureData("Sem valores de fabrico na cloud");

    public final String anomalyType;

    private AnomalyType(String anomalyType) {
        this.anomalyType = anomalyType;
    }
}
