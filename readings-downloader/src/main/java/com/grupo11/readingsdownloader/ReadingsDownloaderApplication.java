package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoDatabaseImpl;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.database.mysql.models.Sensor;
import com.grupo11.readingsdownloader.database.mysql.models.Zona;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import com.grupo11.readingsdownloader.service.DownloadDataService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class ReadingsDownloaderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ReadingsDownloaderApplication.class, args);

        MySQLCloudRepository repository = ctx.getBean(MySQLCloudRepository.class);

        Optional<Zona> zona = repository.getZona(1);
        Optional<Sensor> sensor = repository.getSensor(1, "H");
        //System.out.println(sensor);

        //test
        CloudMongoRepository cloudMongoRepository = ctx.getBean(CloudMongoRepository.class);
        //System.out.println(cloudMongoRepository.findOne());

        CloudMongoDatabaseImpl cloudMongoDatabase = ctx.getBean(CloudMongoDatabaseImpl.class);
        var mostRescent = cloudMongoDatabase
                .getMostRecentData(new ObjectId("60ae8ca8d0907b2de45bcd16"));
        var iterator = mostRescent.iterator();
        while (iterator.hasNext()) {
            Document medicao = (Document) iterator.next();
            System.out.println(medicao);
        }
    }
}


