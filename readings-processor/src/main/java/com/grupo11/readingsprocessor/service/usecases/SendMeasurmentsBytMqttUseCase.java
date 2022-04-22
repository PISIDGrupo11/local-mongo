package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendMeasurmentsBytMqttUseCase {

    private final MQTTSender mqttSender;
    private final MQTTMapper mapper;

    public SendMeasurmentsBytMqttUseCase(MQTTSender mqttSender, MQTTMapper mapper) {
        this.mqttSender = mqttSender;
        this.mapper = mapper;
    }

    public void execute(List<SensorData> measurements) throws MqttException {
        for (SensorData measurement : measurements) {
            mqttSender.send(mapper.mapSensorDataToMedicao(measurement), "readings2");
        }
    }
}
