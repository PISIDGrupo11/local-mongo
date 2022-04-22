package com.grupo11.readingsprocessor;

import com.grupo11.readingsprocessor.mqtt.exceptions.MQTTNotConnectedException;
import com.grupo11.readingsprocessor.service.MqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ReadingsProcessorApplication {

    public static void main(String[] args) throws MqttException, IOException, MQTTNotConnectedException, InterruptedException {
        ApplicationContext ctx = SpringApplication.run(ReadingsProcessorApplication.class, args);

        MqttService mqttService = ctx.getBean(MqttService.class);

        mqttService.runService();
    }
}
