package com.grupo11.readingsprocessor.mqtt;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class MQTTMapper {

    public <T> byte[] mapObjToBytes(T obj) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json.getBytes(StandardCharsets.UTF_8);
    }
}
