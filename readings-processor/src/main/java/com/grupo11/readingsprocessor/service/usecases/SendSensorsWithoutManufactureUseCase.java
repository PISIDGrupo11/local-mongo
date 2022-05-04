package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.Sender;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.mqtt.MQTTMapper;
import com.grupo11.readingsprocessor.service.ReadingsClassifierService;

public class SendSensorsWithoutManufactureUseCase {

    private final Sender sender;
    private final MQTTMapper mapper;
    private final LocalMongoDBRepository repository;

    public SendSensorsWithoutManufactureUseCase(Sender sender,
                                                MQTTMapper mapper,
                                                LocalMongoDBRepository repository) {
        this.sender = sender;
        this.mapper = mapper;
        this.repository = repository;
    }
}
