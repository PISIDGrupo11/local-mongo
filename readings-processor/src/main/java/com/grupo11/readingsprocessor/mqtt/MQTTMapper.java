package com.grupo11.readingsprocessor.mqtt;

import com.google.gson.Gson;
import com.grupo11.readingsprocessor.database.models.Anomalia;
import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.SensorData;
import org.bson.Document;
import com.grupo11.readingsprocessor.database.models.SensorType;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;

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

    public Anomalia mapSensorDataToAnomalia(SensorData sensorData, String tipoAnomalia){
        return new Anomalia(sensorData.getSensor(),
                Integer.parseInt(sensorData.getZona().substring(1)),
                sensorData.getMedicao(),
                tipoAnomalia,
                null,
                sensorData.getId().getDate()
        );
    }

}

