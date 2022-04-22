package com.grupo11.readingsprocessor.service.usecases;

import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartDownloadUseCase {

    private LocalMongoDBRepository localMongoDBRepository;

    public List<SensorData> execute(){

        return localMongoDBRepository.getBulkData();

    }
}
