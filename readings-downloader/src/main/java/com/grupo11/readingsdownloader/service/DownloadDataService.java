package com.grupo11.readingsdownloader.service;

import com.grupo11.readingsdownloader.service.usecases.DowloadLatestDataUseCase;
import org.springframework.stereotype.Service;

@Service
public class DownloadDataService {

    private final DowloadLatestDataUseCase dowloadLatestDataUseCase;

    public DownloadDataService(DowloadLatestDataUseCase dowloadLatestDataUseCase) {
        this.dowloadLatestDataUseCase = dowloadLatestDataUseCase;
    }

    public void runService() throws InterruptedException {
        while (true) {
            dowloadLatestDataUseCase.execute();
            Thread.sleep(2000);
        }
    }
}
