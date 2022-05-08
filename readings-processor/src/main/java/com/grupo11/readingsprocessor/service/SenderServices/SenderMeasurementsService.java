package com.grupo11.readingsprocessor.service.SenderServices;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.service.StartZoneDownloaderService;
import com.mongodb.client.DistinctIterable;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

@Service
@AllArgsConstructor
@EnableAsync
public class SenderMeasurementsService {

    private final StartZoneDownloaderService startZoneDownloaderService;

    private final LocalMongoDBRepository localMongoDBRepository;

    @Async
    public void runService() throws MqttException, NotFoundException {

        HashMap<String, Hashtable<String, Double>> mapManufactureSensorData = localMongoDBRepository
                .getManufactureSensorInformation();

        //can i just use a mapper that will mapp documents to Stack where instead of
        //do another query to localMongo? good practices fuck!!
        DistinctIterable<Integer> zonesNames = localMongoDBRepository.getZonesFromManufactureData();
        Iterator<Integer> itr = zonesNames.iterator();
        while (itr.hasNext()){
            String name = "Z"+ itr.next();
            startZoneDownloaderService.runServer(name, mapManufactureSensorData);
        }
    }
}

