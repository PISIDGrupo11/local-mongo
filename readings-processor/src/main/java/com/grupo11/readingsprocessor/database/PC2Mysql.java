package com.grupo11.readingsprocessor.database;

import com.grupo11.readingsprocessor.database.models.Anomalia;
import com.grupo11.readingsprocessor.database.models.Medicao;
import com.grupo11.readingsprocessor.database.models.UnprocessableEntity;

public interface PC2Mysql {
    void insertMedicao(Medicao medicao);

    void insertAnomalia(Anomalia anomalia);
    void insertUnprocessableEntity(UnprocessableEntity unprocessable);
}
