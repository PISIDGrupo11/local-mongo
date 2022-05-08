package com.grupo11.readingsprocessor.container;

import com.google.gson.Gson;
import com.grupo11.readingsprocessor.Sender;
import com.grupo11.readingsprocessor.database.repository.PC2MysqlRepository;
import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import com.grupo11.readingsprocessor.service.DirectConnectionService;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.zaxxer.hikari.HikariDataSource;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ReadingsProcessorProvider {

    @Value("${spring.data.mongodb.local.uri}")
    private String LOCAL_MONGO_URI;

    @Value("${spring.data.mongodb.local.database}")
    private String LOCAL_MONGO_DB;

    @Value("${broker.uri}")
    private String mqttServer;

    @Bean
    public MongoDatabase provideMongoLocalDatabase() {
        ConnectionString localMongo = new ConnectionString(LOCAL_MONGO_URI);
        MongoClient mongoClient = MongoClients.create(localMongo);
        return mongoClient.getDatabase(LOCAL_MONGO_DB);
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.data.mysql-pc2")
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
        MqttClient mqttClient = new MqttClient(mqttServer, MqttClient.generateClientId());
        mqttClient.connect();
        return mqttClient;
    }


    @Bean
    @Qualifier("mqtt")
    public Sender provideMqttSender(IMqttClient mqttClient, MQTTMapper mqttMapper){
        return new MQTTSender(mqttClient, mqttMapper);
    }

    @Bean
    @Qualifier("direct")
    public Sender provideDirectSender(PC2MysqlRepository pc2MysqlRepository){
        return new DirectConnectionService(pc2MysqlRepository);
    }

    @Bean
    public Gson provideGson() {
        return new Gson();
    }
}
