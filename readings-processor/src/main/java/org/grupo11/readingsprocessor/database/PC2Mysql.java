package org.grupo11.readingsprocessor.database;

import org.grupo11.readingsprocessor.database.models.Anomalia;
import org.grupo11.readingsprocessor.database.models.Medicao;

public interface PC2Mysql {
    void insertMedicao(Medicao medicao);

    void insertAnomalia(Anomalia anomalia);
}
