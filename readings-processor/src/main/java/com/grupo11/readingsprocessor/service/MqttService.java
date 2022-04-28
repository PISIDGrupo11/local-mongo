package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.service.usecases.DowloadLatestDataUseCase;
import com.grupo11.readingsprocessor.service.usecases.SendMeasurmentsBytMqttUseCase;
import com.grupo11.readingsprocessor.service.usecases.StartDownloadUseCase;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MqttService {

    private final StartDownloadUseCase startDownloadUseCase;
    private final DowloadLatestDataUseCase dowloadLatestDataUseCase;
    private final SendMeasurmentsBytMqttUseCase sendMeasurmentsBytMqttUseCase;


    public void runService() throws MqttException, IOException, InterruptedException {

        List<SensorData> measurements = startDownloadUseCase.execute();
        sendMeasurmentsBytMqttUseCase.execute(measurements);
        ObjectId lastObjectId = measurements.get(measurements.size() - 1).getId();

        while (true) {
            Thread.sleep(2000);
            measurements = dowloadLatestDataUseCase.execute(lastObjectId);
            if (measurements.size() != 0) {
                lastObjectId = measurements.get(measurements.size() - 1).getId();
                sendMeasurmentsBytMqttUseCase.execute(measurements);
            }
        }
    }
}
