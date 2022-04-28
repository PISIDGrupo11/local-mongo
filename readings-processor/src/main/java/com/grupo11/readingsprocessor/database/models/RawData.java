package com.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RawData {

    private List<SensorData> sensorDataList;
    private List<UnprocessableEntity> unprocessableEntityList;
}
