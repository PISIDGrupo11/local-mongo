package com.grupo11.readingsdownloader.database.mongodb.repository;

import com.grupo11.readingsdownloader.database.mongodb.models.CloudSQLBackupZone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CloudBackupZoneRepository extends MongoRepository<CloudSQLBackupZone, String> {
}
