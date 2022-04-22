package com.grupo11.readingsprocessor.mqtt;

import com.google.gson.Gson;
import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.SensorData;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MQTTMapper {

    private final Gson gson;

    public MQTTMapper(Gson gson) {
        this.gson = gson;
    }

    public <T> byte[] mapObjToBytes(T obj) {
        String json = gson.toJson(obj);
        return json.getBytes(StandardCharsets.UTF_8);
    }

    public Medicao mapSensorDataToMedicao(SensorData sensorData) {
        return new Medicao(
                Integer.parseInt(sensorData.getZona().substring(1)),
                sensorData.getSensor(),
                sensorData.getMedicao(),
                sensorData.getId().getDate()
        );
    }
}
