package com.grupo11.readingsprocessor.container;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.zaxxer.hikari.HikariDataSource;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

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

    @Bean
    @Primary
    @ConfigurationProperties("spring.data.mysql-cloud")
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
    public IMqttClient provideMQQTCliet() throws MqttException {
        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = new MqttClient("tcp://iot.eclipse.org:1883", publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
        return publisher;
    }
}
