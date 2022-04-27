package com.grupo11.readingsdownloader.database.mysql.mappers;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupSensor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SensorRowMapper implements RowMapper<CloudSQLBackupSensor> {
    @Override
    public CloudSQLBackupSensor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CloudSQLBackupSensor(
                rs.getInt("idsensor"),
                rs.getString("tipo"),
                rs.getFloat("limiteinferior"),
                rs.getFloat("limitesuperior"),
                rs.getInt("idzona")
        );
    }
}
