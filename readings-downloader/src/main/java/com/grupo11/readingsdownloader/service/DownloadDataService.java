package com.grupo11.readingsdownloader.service;

import com.grupo11.readingsdownloader.service.usecases.DowloadLatestDataUseCase;
import com.grupo11.readingsdownloader.service.usecases.StartDownloadUseCase;
import com.grupo11.readingsdownloader.service.usecases.StoreNewDataUseCase;
import org.springframework.stereotype.Service;

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

    public void runService() {

        storeNewDataUseCase.execute(startDownloadUseCase.execute());
    }
}
