package com.grupo11.readingsdownloader.database.mysql.repository;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import com.grupo11.readingsdownloader.database.models.CloudSQLBackupZone;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLCloudRepository implements MySQLCloudDatabase {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CloudSQLBackupSensor> sensorRowMapper;
    private final RowMapper<CloudSQLBackupZone> zonaRowMapper;

    public MySQLCloudRepository(JdbcTemplate jdbcTemplate, RowMapper<CloudSQLBackupSensor> sensorRowMapper,
                                RowMapper<CloudSQLBackupZone> zonaRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.sensorRowMapper = sensorRowMapper;
        this.zonaRowMapper = zonaRowMapper;
    }

    @Override
    public List<CloudSQLBackupSensor> getSensors() {
        String sql = """
                SELECT idsensor, tipo, limiteinferior, limitesuperior, idzona
                FROM sensor;
                """;
        return jdbcTemplate.query(sql, sensorRowMapper);
    }

    @Override
    public List<CloudSQLBackupZone> getZonas() {
        String sql = """
                SELECT idzona, temperatura, humidade, luz
                FROM zona;
                """;
        return jdbcTemplate.query(sql, zonaRowMapper);
    }
}
