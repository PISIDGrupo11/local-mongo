package com.grupo11.readingsdownloader.database.mongodb.repository;

import com.grupo11.readingsdownloader.database.mongodb.models.FilteredData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilteredDataRepository extends MongoRepository<FilteredData, String> {
}
