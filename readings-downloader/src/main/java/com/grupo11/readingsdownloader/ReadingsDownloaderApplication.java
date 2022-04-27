package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.service.DownloadDataService;
import com.grupo11.readingsdownloader.service.usecases.DownloadReferenceValuesUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ReadingsDownloaderApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(ReadingsDownloaderApplication.class, args);

        DownloadDataService downloadDataService = ctx.getBean(DownloadDataService.class);
        downloadDataService.runService();
    }
}


