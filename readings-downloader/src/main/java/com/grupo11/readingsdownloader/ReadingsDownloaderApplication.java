package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.mongodb.models.CloudSQLBackupZone;
import com.grupo11.readingsdownloader.database.mongodb.models.FilteredData;
import com.grupo11.readingsdownloader.database.mongodb.repository.CloudBackupZoneRepository;
import com.grupo11.readingsdownloader.database.mongodb.repository.CloudSQLBackupSensorRepository;
import com.grupo11.readingsdownloader.database.mongodb.repository.FilteredDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class ReadingsDownloaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingsDownloaderApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(FilteredDataRepository filteredRepository,
                             CloudBackupZoneRepository zoneRepository,
                             CloudSQLBackupSensorRepository sensorRepository) {
        return args -> {
            FilteredData data = new FilteredData(
                    "Z1",
                    "T1",
                    LocalDateTime.now(),
                    10.0f,
                    "sdkfjhsdfkj");

            CloudSQLBackupSensor sensor = new CloudSQLBackupSensor(
                    10.0f,
                    2f,
                    1f
            );

            CloudSQLBackupZone zone = new CloudSQLBackupZone(
                    "H",
                    0,
                    10,
                    "23"
            );

            filteredRepository.insert(data);
            sensorRepository.insert(sensor);
            zoneRepository.insert(zone);
        };
    }

}
