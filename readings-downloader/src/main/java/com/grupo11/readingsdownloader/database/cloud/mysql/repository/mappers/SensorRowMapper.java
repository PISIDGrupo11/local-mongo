package com.grupo11.readingsdownloader.database.cloud.mysql.repository.mappers;

import com.grupo11.readingsdownloader.database.cloud.mysql.models.Sensor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SensorRowMapper implements RowMapper<Sensor> {
    @Override
    public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Sensor(
                rs.getInt("idsensor"),
                rs.getString("tipo"),
                rs.getFloat("limiteinferior"),
                rs.getFloat("limitesuperior"),
                rs.getInt("idzona")
        );
    }
}
