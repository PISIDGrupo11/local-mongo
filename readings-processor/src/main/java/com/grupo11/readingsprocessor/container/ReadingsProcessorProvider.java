package com.grupo11.readingsprocessor.container;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadingsProcessorProvider {

    @Value("${spring.data.mongodb.local.uri}")
    private String LOCAL_MONGO_URI;

    @Value("${spring.data.mongodb.local.database}")
    private String LOCAL_MONGO_DB;

    @Bean
    public MongoDatabase provideMongoLocalDatabase() {
        ConnectionString localMongo = new ConnectionString(LOCAL_MONGO_URI);
        MongoClient mongoClient = MongoClients.create(localMongo);
        return mongoClient.getDatabase(LOCAL_MONGO_DB);
    }
}
