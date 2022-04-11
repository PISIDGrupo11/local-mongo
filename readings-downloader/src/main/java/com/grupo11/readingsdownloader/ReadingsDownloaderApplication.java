package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoDatabaseImpl;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudMongoRepository;
import com.grupo11.readingsdownloader.database.mongodb.local.models.FilteredData;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.LocalMongoRepository;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
        //List<CloudSensor> sensors = cloudMongoRepository
                //.getMostRecentData(new ObjectId("60ae8ca8d0907b2de45bcd16"));
        //sensors.forEach(System.out::println);

        LocalMongoRepository localMongoRepository = ctx.getBean(LocalMongoRepository.class);
        localMongoRepository.insertNewFilteredData(List.of(
            new FilteredData(new ObjectId("6036c77f967bf612b4486142"),"Z1", "H1", LocalDateTime.now(), 20f, "timstamp"),
                new FilteredData(new ObjectId("6036c780967bf612b4486144"), "Z1", "H2", LocalDateTime.now(), 30f, "timstamp")
        ));
    }
}


