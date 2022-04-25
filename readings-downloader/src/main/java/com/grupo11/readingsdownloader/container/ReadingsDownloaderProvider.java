package com.grupo11.readingsdownloader.container;

import com.grupo11.readingsdownloader.database.mongodb.cloud.CloudMongoDatabase;
import com.grupo11.readingsdownloader.database.mongodb.cloud.CloudMongoDatabaseImpl;
import com.grupo11.readingsdownloader.database.mongodb.local.LocalMongoDatabase;
import com.grupo11.readingsdownloader.database.mongodb.local.LocalMongoDatabaseImpl;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zaxxer.hikari.HikariDataSource;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ReadingsDownloaderProvider {

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
    @Primary
    @ConfigurationProperties("spring.datasource.mysql-cloud")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }

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
    public LocalMongoDatabase provideLocalMongoDatabase(@Qualifier("local") MongoDatabase mongoDatabase) {
        return new LocalMongoDatabaseImpl(mongoDatabase);
    }

    @Bean
    public CloudMongoDatabase provideCloudMongoDatabase(@Qualifier("cloud") MongoDatabase mongoDatabase) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(CLOUD_MONGO_COLLECTION);
        return new CloudMongoDatabaseImpl(mongoDatabase, collection);
    }
}
