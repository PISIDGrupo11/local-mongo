package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.service.usecases.FetchDataUseCase;
import com.grupo11.readingsprocessor.service.usecases.SendMeasurementsDirectly;
import com.grupo11.readingsprocessor.service.usecases.SendMeasurmentsBytMqttUseCase;
import com.zaxxer.hikari.pool.HikariProxyPreparedStatement;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@Service
public class DirectService {
    private final FetchDataUseCase fetchDataUseCase;
    private final SendMeasurementsDirectly sendMeasurementsdirectly;


    public void runService() throws MqttException, IOException, InterruptedException, NotFoundException {

        while (true) {
            sendMeasurementsdirectly.execute(fetchDataUseCase.execute());
            Thread.sleep(2000);
        }
    }
}
