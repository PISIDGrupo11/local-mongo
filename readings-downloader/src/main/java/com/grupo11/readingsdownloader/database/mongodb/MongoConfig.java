package com.grupo11.readingsdownloader.database.mongodb;

import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoDatabase;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoDatabaseImpl;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoMapper;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoDatabase;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoDatabaseImpl;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoMapper;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.cloud.uri}")
    private String CLOUD_MONGO_URI;

    @Value("${spring.data.mongodb.cloud.database}")
    private String CLOUD_MONGO_DB;

    @Value("${spring.data.mongodb.cloud.collection}")
    private String CLOUD_MONGO_COLLECTION;

    @Value("${spring.data.mongodb.local.uri}")
    private String LOCAL_MONGO_URI;

    @Value("${spring.data.mongodb.local.database}")
    private String LOCAL_MONGO_DB;

    @Bean
    @Qualifier("cloud")
    public MongoDatabase provideMongoCloudDatabase() {
        ConnectionString cloudMongo = new ConnectionString(CLOUD_MONGO_URI);
        MongoClient mongoClient = MongoClients.create(cloudMongo);
        return mongoClient.getDatabase(CLOUD_MONGO_DB);
    }

    @Bean
    @Qualifier("local")
    public MongoDatabase provideMongoLocalDatabase() {
        ConnectionString localMongo = new ConnectionString(LOCAL_MONGO_URI);
        MongoClient mongoClient = MongoClients.create(localMongo);
        return mongoClient.getDatabase(LOCAL_MONGO_DB);
    }

    @Bean
    public LocalMongoMapper provideLocalMongoMapper() {
        return new LocalMongoMapper();
    }

    @Bean
    public CloudMongoMapper provideCloudMongoMapper() {
        return new CloudMongoMapper();
    }

    @Bean
    @Qualifier("local")
    public LocalMongoDatabase provideLocalMongoDatabase(@Qualifier("local") MongoDatabase mongoDatabase) {
        return new LocalMongoDatabaseImpl(mongoDatabase);
    }

    @Bean
    @Qualifier("cloud")
    public CloudMongoDatabase provideCloudMongoDatabase(@Qualifier("cloud") MongoDatabase mongoDatabase) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(CLOUD_MONGO_COLLECTION);
        return new CloudMongoDatabaseImpl(mongoDatabase, collection);
    }

}
