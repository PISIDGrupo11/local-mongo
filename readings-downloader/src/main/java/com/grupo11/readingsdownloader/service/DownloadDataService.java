package com.grupo11.readingsdownloader.service;

import com.grupo11.readingsdownloader.service.usecases.DowloadLatestDataUseCase;
import com.grupo11.readingsdownloader.service.usecases.ValidadeDataUseCase;
import org.springframework.stereotype.Service;

@Service
public class DownloadDataService {

    private final DowloadLatestDataUseCase dowloadLatestDataUseCase;
    private final ValidadeDataUseCase validadeDataUseCase;

    public DownloadDataService(DowloadLatestDataUseCase dowloadLatestDataUseCase,
                               ValidadeDataUseCase validadeDataUseCase) {
        this.dowloadLatestDataUseCase = dowloadLatestDataUseCase;
        this.validadeDataUseCase = validadeDataUseCase;
    }

    public void runService() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
