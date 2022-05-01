package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.service.usecases.FetchDataUseCase;
import com.grupo11.readingsprocessor.service.usecases.SendMeasurmentsBytMqttUseCase;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@Service
@AllArgsConstructor
public class MqttService {

    private final FetchDataUseCase fetchDataUseCase;
    private final SendMeasurmentsBytMqttUseCase sendMeasurmentsBytMqttUseCase;

    private final LocalMongoDBRepository localMongoDBRepository;


    public void runService() throws MqttException, InterruptedException, NotFoundException {
        HashMap<String, Hashtable<String, Double>> mapManufactureSensorData = localMongoDBRepository.
                getManufactureSensorInformation();

        while (true) {
            sendMeasurmentsBytMqttUseCase.execute(fetchDataUseCase.execute(), mapManufactureSensorData);
            Thread.sleep(2000);
        }
    }
}
