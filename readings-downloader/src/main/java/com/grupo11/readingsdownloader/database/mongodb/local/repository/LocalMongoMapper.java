package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.models.CloudSQLBackupZone;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocalMongoMapper {

    public Document mapCloudBackupZoneToDocument(CloudSQLBackupZone cloudSQLBackupZone) {
        Document document = new Document();
        document.append("idZona", cloudSQLBackupZone.getIdZona());
        document.append("temperatura", cloudSQLBackupZone.getTemperatura());
        document.append("humidade", cloudSQLBackupZone.getHumidade());
        document.append("luz", cloudSQLBackupZone.getLuz());
        return document;
    }

    public Document mapCloudBackupSensorToDocument(CloudSQLBackupSensor cloudSQLBackupSensor) {
        Document document = new Document();
        document.append("idSensor", cloudSQLBackupSensor.getIdSensor());
        document.append("tipo", cloudSQLBackupSensor.getTipo());
        document.append("limiteInferior", cloudSQLBackupSensor.getLimiteInferior());
        document.append("limiteSuperior", cloudSQLBackupSensor.getLimiteSuperior());
        document.append("idZona", cloudSQLBackupSensor.getIdZona());
        return document;
    }

    public Optional<ObjectId> mapDocumentToObjectId(Document document) {
        return Optional.ofNullable(document.getObjectId("_id"));
    }
}
