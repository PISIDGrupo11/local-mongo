package com.grupo11.readingsprocessor.service.Senders;

import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.mqtt.Topics;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MQTTSender implements Sender {
    private final IMqttClient mqttClient;
    private final MQTTMapper mapper;

    public <T> void send(T obj, String topic) throws MqttException {
        System.out.println("[" + topic + "] Sending " + obj.toString());

        MqttMessage mqttMessage = new MqttMessage(mapper.mapObjToBytes(obj));
        setQos(mqttMessage,topic);
        mqttMessage.setRetained(true);
        mqttClient.publish(topic, mqttMessage);
    }

    private void setQos(MqttMessage message,String topic){
        if (topic.equals(Topics.Anomaly)) {
            message.setQos(2);
        } else {
            message.setQos(0);
        }
    }
}
