package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.PC2Mysql;
import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.database.models.Anomalia;
import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.UnprocessableEntity;
import com.grupo11.readingsprocessor.database.repository.PC2MysqlRepository;
import com.grupo11.readingsprocessor.mqtt.Sender;
import com.grupo11.readingsprocessor.mqtt.Topics;
import com.grupo11.readingsprocessor.service.usecases.FetchDataUseCase;
import com.grupo11.readingsprocessor.service.usecases.ManufacturingErrorDetection;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;

@Service("DirectSender")
@AllArgsConstructor
public class DirectConnectionService implements Sender {

    private PC2MysqlRepository pc2MysqlRepository;

    @Override
    public <T> void send(T obj, String topic) {

        System.out.println(obj);

        if(topic.equals(Topics.Reading)){
            System.out.println(obj);
            pc2MysqlRepository.insertMedicao((Medicao) obj);

        }
        else if(topic.equals(Topics.Anomaly)) {
            pc2MysqlRepository.insertAnomalia((Anomalia) obj);
        }
        else{
            System.out.println(obj);
            pc2MysqlRepository.insertUnprocessableEntity((UnprocessableEntity) obj);
        }
    }
}
