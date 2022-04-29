package com.grupo11.readingsprocessor;

import com.grupo11.readingsprocessor.database.models.SensorData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilterSensorData {

    private String type;
    private SensorData sensorData;
    private String mqttTopic;
}
