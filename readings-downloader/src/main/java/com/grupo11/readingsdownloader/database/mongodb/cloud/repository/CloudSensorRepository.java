package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CloudSensorRepository extends MongoRepository<CloudSensor, String> {

    Optional<CloudSensor> findCloudSensorByMedicaoEquals(String medicao);
}
