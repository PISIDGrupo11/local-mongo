package com.grupo11.readingsdownloader.service;

import com.grupo11.readingsdownloader.database.mongodb.cloud.models.CloudSensor;
import com.grupo11.readingsdownloader.service.usecases.DowloadLatestDataUseCase;
import com.grupo11.readingsdownloader.service.usecases.StartDownloadUseCase;
import com.grupo11.readingsdownloader.service.usecases.StoreNewDataUseCase;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadDataService {

    private final DowloadLatestDataUseCase dowloadLatestDataUseCase;
    private final StoreNewDataUseCase storeNewDataUseCase;
    private final StartDownloadUseCase startDownloadUseCase;

    public DownloadDataService(DowloadLatestDataUseCase dowloadLatestDataUseCase,
                               StoreNewDataUseCase storeNewDataUseCase,
                               StartDownloadUseCase startDownloadUseCase) {
        this.dowloadLatestDataUseCase = dowloadLatestDataUseCase;
        this.storeNewDataUseCase = storeNewDataUseCase;
        this.startDownloadUseCase = startDownloadUseCase;
    }

    public void runService() throws InterruptedException {

        List<CloudSensor> cloudSensorsList = startDownloadUseCase.execute();
        ObjectId lastObjectId = cloudSensorsList.get(cloudSensorsList.size() - 1).getId();
        storeNewDataUseCase.execute(cloudSensorsList);

        while (true){

            Thread.sleep(2000);
            cloudSensorsList = dowloadLatestDataUseCase.execute(lastObjectId);
            lastObjectId = cloudSensorsList.get(cloudSensorsList.size() - 1).getId();
            storeNewDataUseCase.execute(cloudSensorsList);
        }
    }
}
