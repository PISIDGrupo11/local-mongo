package com.grupo11.readingsprocessor.service.SenderServices;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.service.usecases.FetchSensorsWithoutManufactureUseCase;
import com.grupo11.readingsprocessor.service.usecases.SendSensorsWithoutManufactureUseCase;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SenderAnomalyCollectionService {


    private FetchSensorsWithoutManufactureUseCase fetchSensorsWithoutManufactureUseCase;

    private SendSensorsWithoutManufactureUseCase sendSensorsWithoutManufactureUseCase;

    @Async
    public void runService() throws NotFoundException, MqttException, InterruptedException {

        System.out.println("Start: SenderAnomalyCollectionService");
        while(true){
            System.out.println("I AM ALIVE!");
            sendSensorsWithoutManufactureUseCase
                    .execute(fetchSensorsWithoutManufactureUseCase.execute());
            Thread.sleep(60000);
        }

    }

}
