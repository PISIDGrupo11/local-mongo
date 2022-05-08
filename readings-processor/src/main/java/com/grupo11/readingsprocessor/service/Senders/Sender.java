package com.grupo11.readingsprocessor.service.Senders;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface Sender {

    public <T> void send(T obj, String topic) throws MqttException;
}
