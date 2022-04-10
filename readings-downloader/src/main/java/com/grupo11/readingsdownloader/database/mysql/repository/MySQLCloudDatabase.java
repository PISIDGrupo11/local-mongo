package com.grupo11.readingsdownloader.database.mysql.repository;

import com.grupo11.readingsdownloader.database.mysql.models.Sensor;
import com.grupo11.readingsdownloader.database.mysql.models.Zona;

import java.util.Optional;

public interface MySQLCloudDatabase {
    Optional<Sensor> getSensor(int id, String tipo);

    Optional<Zona> getZona(int id);
}
