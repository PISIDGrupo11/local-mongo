package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.service.usecases.FetchSensorsWithoutManufactureUseCase;
import com.grupo11.readingsprocessor.service.usecases.SendSensorsWithoutManufactureUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SenderAnomalyCollectionService {


    private FetchSensorsWithoutManufactureUseCase fetchSensorsWithoutManufactureUseCase;

    private SendSensorsWithoutManufactureUseCase sendSensorsWithoutManufactureUseCase;

}
