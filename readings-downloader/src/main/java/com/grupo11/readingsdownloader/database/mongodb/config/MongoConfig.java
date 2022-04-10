package com.grupo11.readingsdownloader.database.mongodb.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    @Primary
    @Bean(name = "localProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.local")
    public MongoProperties getLocalProps() {
        return new MongoProperties();
    }

    @Bean(name = "cloudProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.cloud")
    public MongoProperties getCloudProps() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "localMongoTemplate")
    public MongoTemplate getLocalMongoTemplate() {
        return new MongoTemplate(localDatabaseFactory(getLocalProps()));
    }

    @Bean(name = "cloudMongoTemplate")
    public MongoTemplate getCloudMongoTemplate() {
        return new MongoTemplate(cloudDatabaseFactory(getCloudProps()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory localDatabaseFactory(MongoProperties localProps) {
        return new SimpleMongoClientDatabaseFactory(localProps.getUri());
    }

    @Bean
    public MongoDatabaseFactory cloudDatabaseFactory(MongoProperties cloudProps) {
        return new SimpleMongoClientDatabaseFactory(cloudProps.getUri());

    }
}
