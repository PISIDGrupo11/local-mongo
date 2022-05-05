package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.database.models.Anomalia;
import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.UnprocessableEntity;
import com.grupo11.readingsprocessor.database.repository.PC2MysqlRepository;
import com.grupo11.readingsprocessor.Sender;
import com.grupo11.readingsprocessor.mqtt.Topics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
