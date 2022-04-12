package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupZone;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class LocalMongoMapper {

    public Document mapFilteredDataToDocument(CloudSensor filteredData) {
        Document document = new Document();
        document.append("_id", filteredData.getId());
        document.append("zona", filteredData.getZona());
        document.append("sensor", filteredData.getSensor());
        document.append("data", filteredData.getData());
        document.append("medicao", filteredData.getMedicao());
        return document;
    }

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

    public CloudSQLBackupZone mapDocumentToCloudBackupZone(Document document) {
        return new CloudSQLBackupZone(
                document.getInteger("idZona"),
                document.getDouble("temperatura"),
                document.getDouble("humidade"),
                document.getDouble("luz")
        );
    }

    public CloudSQLBackupSensor mapDocumentToCloudBackupSensor(Document document) {
        return new CloudSQLBackupSensor(
                document.getInteger("idSensor"),
                document.getString("tipo"),
                document.getDouble("limiteInferior"),
                document.getDouble("limiteSuperior"),
                document.getString("idZona")
        );
    }
}
