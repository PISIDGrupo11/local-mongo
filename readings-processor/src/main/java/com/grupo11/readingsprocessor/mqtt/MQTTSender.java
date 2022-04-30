package com.grupo11.readingsprocessor.mqtt;

import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MQTTSender {
    private final IMqttClient mqttClient;
    private final MQTTMapper mapper;

    public <T> void send(T obj, String topic) throws MqttException {
        System.out.println("[" + topic + "] Sending " + obj.toString());

        MqttMessage mqttMessage = new MqttMessage(mapper.mapObjToBytes(obj));
        mqttMessage.setQos(2);
        mqttMessage.setRetained(true);
        mqttClient.publish(topic, mqttMessage);
    }
}
