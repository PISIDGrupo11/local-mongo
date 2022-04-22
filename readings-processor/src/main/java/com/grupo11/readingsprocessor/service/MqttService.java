package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import com.grupo11.readingsprocessor.mqtt.exceptions.MQTTNotConnectedException;
import com.grupo11.readingsprocessor.service.usecases.DowloadLatestDataUseCase;
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
    private final MQTTSender mqttService;
    private final DowloadLatestDataUseCase dowloadLatestDataUseCase;


    private void sendMeasurements(List<SensorData> measurements) throws MqttException, IOException, MQTTNotConnectedException {
        for (SensorData measurement : measurements) {
            mqttService.send(measurement, "readings2");
        }
    }


    public void runService() throws MQTTNotConnectedException, MqttException, IOException, InterruptedException {

        List<SensorData> measurements = startDownloadUseCase.execute();
        sendMeasurements(measurements);
        ObjectId lastObjectId = measurements.get(measurements.size() - 1).getId();

        while (true){
            Thread.sleep(2000);
            measurements = dowloadLatestDataUseCase.execute(lastObjectId);
            lastObjectId = measurements.get(measurements.size() - 1).getId();
            sendMeasurements(measurements);
        }
    }
}
