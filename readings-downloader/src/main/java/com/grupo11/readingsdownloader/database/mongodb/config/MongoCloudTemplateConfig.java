package com.grupo11.readingsdownloader.database.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.grupo11.readingsdownloader.database.mongodb.cloud.repository"},
        mongoTemplateRef = MongoCloudTemplateConfig.MONGO_TEMPLATE)
public class MongoCloudTemplateConfig {
    protected static final String MONGO_TEMPLATE = "cloudMongoTemplate";
}
