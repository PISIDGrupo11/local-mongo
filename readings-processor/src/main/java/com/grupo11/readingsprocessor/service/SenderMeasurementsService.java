package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.service.usecases.FetchDataUseCase;
import com.grupo11.readingsprocessor.service.usecases.SendMeasurmentsUseCase;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;

@Service
@AllArgsConstructor
public class SenderMeasurementsService {

    private final FetchDataUseCase fetchDataUseCase;
    private final SendMeasurmentsUseCase sendMeasurmentsUseCase;

    private final LocalMongoDBRepository localMongoDBRepository;


    public void runService() throws MqttException, InterruptedException, NotFoundException {
        HashMap<String, Hashtable<String, Double>> mapManufactureSensorData = localMongoDBRepository.
                getManufactureSensorInformation();

        while (true) {
            sendMeasurmentsUseCase.execute(fetchDataUseCase.execute(), mapManufactureSensorData);
            Thread.sleep(2000);
        }
    }
}
