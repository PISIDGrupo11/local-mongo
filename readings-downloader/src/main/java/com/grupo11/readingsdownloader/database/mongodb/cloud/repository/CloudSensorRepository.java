package com.grupo11.readingsdownloader.database.mongodb.cloud.repository;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CloudSensorRepository extends MongoRepository<CloudSensor, String> {

    List<CloudSensor> findCloudSensorByMedicaoEquals(String Medicao);
//@Query("{'Data':{ $gt: ?0}}).limit(20)")
    List<CloudSensor> findCloudSensorsByDataEquals(String Data);
}
