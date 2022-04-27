package com.grupo11.readingsdownloader.service;

import com.grupo11.readingsdownloader.service.usecases.DowloadLatestDataUseCase;
import com.grupo11.readingsdownloader.service.usecases.DownloadReferenceValuesUseCase;
import org.springframework.stereotype.Service;

@Service
public class DownloadDataService {

    private final DowloadLatestDataUseCase dowloadLatestDataUseCase;
    private final DownloadReferenceValuesUseCase downloadReferenceValuesUseCase;

    public DownloadDataService(DowloadLatestDataUseCase dowloadLatestDataUseCase,
                               DownloadReferenceValuesUseCase downloadReferenceValuesUseCase) {
        this.dowloadLatestDataUseCase = dowloadLatestDataUseCase;
        this.downloadReferenceValuesUseCase = downloadReferenceValuesUseCase;
    }

    public void runService() throws InterruptedException {
        downloadReferenceValuesUseCase.execute();
        while (true) {
            dowloadLatestDataUseCase.execute();
            Thread.sleep(2000);
        }
    }
}
