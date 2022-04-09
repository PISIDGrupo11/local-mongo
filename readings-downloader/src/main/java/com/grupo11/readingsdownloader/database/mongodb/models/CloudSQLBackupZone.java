package com.grupo11.readingsdownloader.database.mongodb.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class CloudSQLBackupZone {
    @MongoId
    private String id;
    private String tipo;
    private float limiteInferior;
    private float limiteSuperior;
    private String idZona;

    public CloudSQLBackupZone(String tipo, float limiteInferior, float limiteSuperior, String idZona) {
        this.tipo = tipo;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.idZona = idZona;
    }
}
