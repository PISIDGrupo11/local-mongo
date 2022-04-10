package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.mongodb.local.models.CloudSQLBackupZone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CloudBackupZoneRepository extends MongoRepository<CloudSQLBackupZone, String> {
}
