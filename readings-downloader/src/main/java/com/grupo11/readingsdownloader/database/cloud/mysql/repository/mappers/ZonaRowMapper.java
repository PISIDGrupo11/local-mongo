package com.grupo11.readingsdownloader.database.cloud.mysql.repository.mappers;

import com.grupo11.readingsdownloader.database.cloud.mysql.models.Zona;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ZonaRowMapper implements RowMapper<Zona> {
    @Override
    public Zona mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Zona(
                rs.getInt("idzona"),
                rs.getFloat("temperatura"),
                rs.getFloat("humidade"),
                rs.getFloat("luz")
        );
    }
}
