package com.grupo11.readingsdownloader.database.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.grupo11.readingsdownloader.database.mongodb.local.repository"},
        mongoTemplateRef = MongoLocalTemplateConfig.MONGO_TEMPLATE)
public class MongoLocalTemplateConfig {
    protected static final String MONGO_TEMPLATE = "localMongoTemplate";
}
