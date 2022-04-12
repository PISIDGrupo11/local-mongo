package com.grupo11.readingsdownloader.database.mongodb.local.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "CloudSQLBackupSensor")
public class CloudSQLBackupSensor {
    @Id
    private ObjectId id;
    private int idSensor;
    private String tipo;
    private double limiteInferior;
    private double limiteSuperior;
    private String idZona;

    public CloudSQLBackupSensor(int idSensor, String tipo, double limiteInferior, double limiteSuperior, String idZona) {
        this.idSensor = idSensor;
        this.tipo = tipo;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.idZona = idZona;
    }
}
