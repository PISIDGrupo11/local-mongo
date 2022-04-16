package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.mqtt.MQTTSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MqttService {

    private final MQTTSender mqttService;
}
