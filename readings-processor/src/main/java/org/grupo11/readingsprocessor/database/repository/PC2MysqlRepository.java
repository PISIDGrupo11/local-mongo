package org.grupo11.readingsprocessor.database.repository;

import org.grupo11.readingsprocessor.database.models.Medicao;
import org.grupo11.readingsprocessor.database.PC2Mysql;
import org.grupo11.readingsprocessor.database.models.Anomalia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PC2MysqlRepository implements PC2Mysql {

    private final JdbcTemplate jdbcTemplate;


    public PC2MysqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertMedicao(Medicao medicao) {
        String query = """
                INSERT INTO medicao (Zona, Sensor, DataHora, Leitura, DataHoraObjectId)
                VALUES (?, ?, ?, ?, ?);
                """;
        jdbcTemplate.update(query, medicao.getZona(), medicao.getSensor(), medicao.getDataHora(),
                medicao.getLeitura(), medicao.getDataHoraObjectId());
    }

    @Override
    public void insertAnomalia(Anomalia anomalia) {
        String query = """
                INSERT INTO anomalia (IDSensor, ValorAnomalo, TipoAnomalia, Hora)
                VALUES (?, ?, ?, ?, ?);
                """;
        jdbcTemplate.update(query, anomalia.getIDSensor(), anomalia.getValorAnomalo(), anomalia.getTipoAnomalia(),
                anomalia.getHora());
    }
}
