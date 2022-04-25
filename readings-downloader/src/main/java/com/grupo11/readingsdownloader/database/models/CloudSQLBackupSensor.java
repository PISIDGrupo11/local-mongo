package com.grupo11.readingsdownloader.database.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "CloudSQLBackupSensor")
public class CloudSQLBackupSensor {
    @Id
    private ObjectId id;
    private int idSensor;
    private String tipo;
    private double limiteInferior;
    private double limiteSuperior;
    private int idZona;

    public CloudSQLBackupSensor(int idSensor, String tipo, double limiteInferior, double limiteSuperior, int idZona) {
        this.idSensor = idSensor;
        this.tipo = tipo;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.idZona = idZona;
    }
}
