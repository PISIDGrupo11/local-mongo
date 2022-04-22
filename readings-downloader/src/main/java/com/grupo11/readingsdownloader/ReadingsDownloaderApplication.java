package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
import com.grupo11.readingsdownloader.database.mysql.models.Sensor;
import com.grupo11.readingsdownloader.database.mysql.models.Zona;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import com.grupo11.readingsdownloader.service.DownloadDataService;
import com.grupo11.readingsdownloader.service.usecases.DowloadLatestDataUseCase;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class ReadingsDownloaderApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(ReadingsDownloaderApplication.class, args);


        //test
        DownloadDataService downloadDataService = ctx.getBean(DownloadDataService.class);
        downloadDataService.runService();


    }
}


