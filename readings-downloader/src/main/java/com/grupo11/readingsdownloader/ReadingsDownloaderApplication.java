package com.grupo11.readingsdownloader;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.database.mongodb.cloud.repository.CloudSensorRepository;
import com.grupo11.readingsdownloader.database.mongodb.local.models.FilteredData;
import com.grupo11.readingsdownloader.database.mongodb.local.repository.FilteredDataRepository;
import com.grupo11.readingsdownloader.database.mysql.models.Sensor;
import com.grupo11.readingsdownloader.database.mysql.models.Zona;
import com.grupo11.readingsdownloader.database.mysql.repository.MySQLCloudRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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

        CloudSensorRepository cloudSensorRepository = ctx.getBean(CloudSensorRepository.class);

        cloudSensorRepository.findCloudSensorByMedicaoEquals("30");
    }


}
