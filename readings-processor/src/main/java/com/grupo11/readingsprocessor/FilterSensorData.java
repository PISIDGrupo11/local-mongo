package com.grupo11.readingsprocessor;

import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.models.SensorDataClassification;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class FilterSensorData {

    private SensorDataClassification classification;
    private SensorData sensorData;
    private String mqttTopic;
}
