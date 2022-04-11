package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.database.mysql.models.Sensor;
import com.grupo11.readingsdownloader.database.mysql.models.Zona;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class ReadingsDownloaderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ReadingsDownloaderApplication.class, args);

        MySQLCloudRepository repository = ctx.getBean(MySQLCloudRepository.class);

        Optional<Zona> zona = repository.getZona(1);
        Optional<Sensor> sensor = repository.getSensor(1, "H");
        System.out.println(sensor);

        //test
        CloudMongoRepository cloudMongoRepository = ctx.getBean(CloudMongoRepository.class);
        System.out.println(cloudMongoRepository.findOne());
    }
}


