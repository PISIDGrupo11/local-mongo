package com.grupo11.readingsdownloader.database.mysql.mappers;

import com.grupo11.readingsdownloader.database.models.CloudSQLBackupZone;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ZonaRowMapper implements RowMapper<CloudSQLBackupZone> {
    @Override
    public CloudSQLBackupZone mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CloudSQLBackupZone(
                rs.getInt("idzona"),
                rs.getFloat("temperatura"),
                rs.getFloat("humidade"),
                rs.getFloat("luz")
        );
    }
}
