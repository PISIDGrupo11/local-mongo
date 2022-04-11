package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudSensorRepository;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudSensorRepositoryImp;
import com.grupo11.readingsdownloader.database.mongodb.local.models.FilteredData;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.FilteredDataRepository;
import com.grupo11.readingsdownloader.database.mysql.models.Sensor;
import com.grupo11.readingsdownloader.database.mysql.models.Zona;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ReadingsDownloaderApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ReadingsDownloaderApplication.class, args);

        MySQLCloudRepository repository = ctx.getBean(MySQLCloudRepository.class);

        Optional<Zona> zona = repository.getZona(1);
        Optional<Sensor> sensor = repository.getSensor(1, "H");
        System.out.println(sensor);

        FilteredDataRepository filteredDataRepository = ctx.getBean(FilteredDataRepository.class);

        CloudSensorRepositoryImp cloudSensorRepository = ctx.getBean(CloudSensorRepositoryImp.class);
       // Iterable<CloudSensor> l= cloudSensorRepository.findAll();
cloudSensorRepository.findCloudSensorByMedicaoEquals("20.0");
        //for(CloudSensor c:l)
            //System.out.println(c);
    }

    CommandLineRunner runner(CloudSensorRepository repository, MongoTemplate mongoTemplate){
        return args -> {
            Query q = new Query();
            q.addCriteria(Criteria.where("Medicao").is("24.61639494871795"));
            List<CloudSensor> l =mongoTemplate.find(q,CloudSensor.class);
            System.out.println(l);
        };}
}


