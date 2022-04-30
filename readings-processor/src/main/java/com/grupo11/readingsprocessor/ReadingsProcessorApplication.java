package com.grupo11.readingsprocessor;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.mqtt.exceptions.MQTTNotConnectedException;
import com.grupo11.readingsprocessor.service.DirectService;
import com.grupo11.readingsprocessor.service.MqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ReadingsProcessorApplication {

    public static void main(String[] args) throws MqttException, IOException, InterruptedException, NotFoundException {
        ApplicationContext ctx = SpringApplication.run(ReadingsProcessorApplication.class, args);

        /*MqttService mqttService = ctx.getBean(MqttService.class);
        mqttService.runService();*/
        DirectService directService=ctx.getBean(DirectService.class);
        directService.runService();

    }
}
