package com.grupo11.readingsprocessor.mqtt.exceptions;

public class MQTTNotConnectedException extends Exception {
    public MQTTNotConnectedException() {
        super("MQTTClient could not connect");
    }
}
