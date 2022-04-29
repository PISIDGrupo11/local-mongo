package com.grupo11.readingsprocessor.database.models;

public enum AnomalyType {

    SensorFailure("Avaria do Sensor"),
    SporadicEvent("Eventos Esporádicos"),
    InvalidValue("Valor Inválido");

    public final String anomalyType;

    private AnomalyType(String anomalyType) {
        this.anomalyType = anomalyType;
    }
}
