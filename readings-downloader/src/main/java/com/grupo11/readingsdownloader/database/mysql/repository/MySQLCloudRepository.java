package com.grupo11.readingsdownloader.database.mysql.repository;

import com.grupo11.readingsdownloader.database.mysql.models.Sensor;
import com.grupo11.readingsdownloader.database.mysql.models.Zona;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySQLCloudRepository implements MySQLCloudDatabase {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Sensor> sensorRowMapper;
    private final RowMapper<Zona> zonaRowMapper;

    public MySQLCloudRepository(JdbcTemplate jdbcTemplate, RowMapper<Sensor> sensorRowMapper,
                                RowMapper<Zona> zonaRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.sensorRowMapper = sensorRowMapper;
        this.zonaRowMapper = zonaRowMapper;
    }

    @Override
    public Optional<Sensor> getSensor(int id, String tipo) {
        String sql = """
                SELECT idsensor, tipo, limiteinferior, limitesuperior, idzona
                FROM sensor
                WHERE idsensor = ? AND tipo = ?;
                """;
        List<Sensor> sensors = jdbcTemplate.query(sql, sensorRowMapper, id, tipo);
        return sensors.stream().findFirst();
    }

    @Override
    public Optional<Zona> getZona(int id) {
        String sql = """
                SELECT idzona, temperatura, humidade, luz
                FROM zona
                WHERE idzona = ?;
                """;
        List<Zona> zonas = jdbcTemplate.query(sql, zonaRowMapper, id);
        return zonas.stream().findFirst();
    }
}
