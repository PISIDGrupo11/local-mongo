package com.grupo11.readingsprocessor.mqtt.exceptions;

public class MQTTNotConnectedException extends Exception{
    public MQTTNotConnectedException(String message) {
        super("MQTTClient could not connect");
    }
}
