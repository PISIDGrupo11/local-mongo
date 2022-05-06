package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.service.usecases.FetchDataUseCase;
import com.grupo11.readingsprocessor.service.usecases.SendMeasurmentsUseCase;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;

@Service
@AllArgsConstructor
public class StartZoneDownloaderService {

    private final FetchDataUseCase fetchDataUseCase;

    private final SendMeasurmentsUseCase sendMeasurmentsUseCase;



    @Async
    public void runServer(String zone,
                          HashMap<String,Hashtable<String,Double>> manufactureInformation
    ) throws NotFoundException, MqttException {

        while(true) {
            sendMeasurmentsUseCase
                    .execute(fetchDataUseCase.execute(zone), manufactureInformation, zone);

        }
    }
}
