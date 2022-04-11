package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CloudSensorRepository extends MongoRepository<CloudSensor, String> {

    List<CloudSensor> findCloudSensorByMedicaoEquals(String Medicao);
    Optional<CloudSensor> findCloudSensorsByDataEquals(String Data);
}
