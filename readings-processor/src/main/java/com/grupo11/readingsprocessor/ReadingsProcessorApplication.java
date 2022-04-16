package com.grupo11.readingsprocessor;

import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import com.grupo11.readingsprocessor.mqtt.exceptions.MQTTNotConnectedException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ReadingsProcessorApplication {

    public static void main(String[] args) throws MqttException, IOException, MQTTNotConnectedException {
        ApplicationContext ctx = SpringApplication.run(ReadingsProcessorApplication.class, args);

        MQTTSender mqttSender = ctx.getBean(MQTTSender.class);

        mqttSender.send(55, "readings");

    }

}
