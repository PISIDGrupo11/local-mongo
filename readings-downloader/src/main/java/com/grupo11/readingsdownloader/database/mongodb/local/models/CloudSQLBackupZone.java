package com.grupo11.readingsdownloader.database.mongodb.local.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "CloudSQLBackupZone")
public class CloudSQLBackupZone {
    @Id
    private ObjectId id;
    private int idZona;
    private double temperatura;
    private double humidade;
    private double luz;

    public CloudSQLBackupZone(int idZona, double temperatura, double humidade, double luz) {
        this.idZona = idZona;
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.luz = luz;
    }
}
