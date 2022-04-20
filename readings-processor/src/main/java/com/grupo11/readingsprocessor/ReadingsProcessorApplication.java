package com.grupo11.readingsprocessor;

import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import com.grupo11.readingsprocessor.mqtt.exceptions.MQTTNotConnectedException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class ReadingsProcessorApplication {

    public static void main(String[] args) throws MqttException, IOException, MQTTNotConnectedException {
        ApplicationContext ctx = SpringApplication.run(ReadingsProcessorApplication.class, args);

        MQTTSender mqttSender = ctx.getBean(MQTTSender.class);

        mqttSender.send(new Medicao(334, 1, "t1", new Date(), 2.1, new Date()),
                "readings2");

    }

}
