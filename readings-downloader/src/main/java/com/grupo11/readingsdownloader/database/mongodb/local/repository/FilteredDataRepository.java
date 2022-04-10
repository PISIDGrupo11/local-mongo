package com.grupo11.readingsdownloader.database.mongodb.local.repository;

import com.grupo11.readingsdownloader.database.mongodb.local.models.FilteredData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilteredDataRepository extends MongoRepository<FilteredData, String> {
}
