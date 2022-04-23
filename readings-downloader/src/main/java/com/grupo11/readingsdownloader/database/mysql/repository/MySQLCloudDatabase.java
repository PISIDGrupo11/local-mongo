package com.grupo11.readingsdownloader.database.mysql.repository;

import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupZone;

import java.util.List;

public interface MySQLCloudDatabase {

    List<CloudSQLBackupSensor> getSensors();

    List<CloudSQLBackupZone> getZonas();
}
