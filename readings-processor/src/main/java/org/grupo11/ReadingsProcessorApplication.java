package org.grupo11;

import org.grupo11.Serializable.Anomalia;
import org.grupo11.Serializable.Medicao;
import org.grupo11.readingsprocessor.mqtt.MQTTSender;
import org.grupo11.readingsprocessor.mqtt.exceptions.MQTTNotConnectedException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootApplication
public class ReadingsProcessorApplication {

    public static void main(String[] args) throws MqttException, IOException, MQTTNotConnectedException {
        ApplicationContext ctx = SpringApplication.run(ReadingsProcessorApplication.class, args);

        MQTTSender mqttSender = ctx.getBean(MQTTSender.class);

        Anomalia anomalia = new Anomalia(1,"t5",3.5, "das mas",LocalDateTime.now());
        mqttSender.send(anomalia, "readings");
        Medicao medicao = new Medicao(1,1,"t2",LocalDateTime.now(), 2.4,LocalDateTime.now() );
        mqttSender.send(medicao, "medicoes");

    }

}
