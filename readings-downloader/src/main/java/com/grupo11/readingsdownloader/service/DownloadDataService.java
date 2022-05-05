package com.grupo11.readingsdownloader.service;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.models.RawData;
import com.grupo11.readingsdownloader.service.usecases.DowloadLatestDataUseCase;
import com.grupo11.readingsdownloader.service.usecases.DownloadReferenceValuesUseCase;
import com.grupo11.readingsdownloader.service.usecases.SeparateDataToCategoriesUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadDataService {

    private final DowloadLatestDataUseCase dowloadLatestDataUseCase;
    private final DownloadReferenceValuesUseCase downloadReferenceValuesUseCase;

    private final SeparateDataToCategoriesUseCase separateDataToCategoriesUseCase;

    public DownloadDataService(DowloadLatestDataUseCase dowloadLatestDataUseCase,
                               DownloadReferenceValuesUseCase downloadReferenceValuesUseCase,
                               SeparateDataToCategoriesUseCase separateDataToCategoriesUseCase) {
        this.dowloadLatestDataUseCase = dowloadLatestDataUseCase;
        this.downloadReferenceValuesUseCase = downloadReferenceValuesUseCase;
        this.separateDataToCategoriesUseCase = separateDataToCategoriesUseCase;
    }

    public void runService() throws InterruptedException {
        List<CloudSQLBackupSensor> cloudSQLBackupSensorList = downloadReferenceValuesUseCase.execute();
        while (true) {
            RawData rawData = separateDataToCategoriesUseCase.execute(
                    cloudSQLBackupSensorList);
            dowloadLatestDataUseCase.execute(rawData, cloudSQLBackupSensorList);
            Thread.sleep(1000);
        }
    }
}
