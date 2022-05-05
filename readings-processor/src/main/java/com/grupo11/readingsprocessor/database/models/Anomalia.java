package com.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
public class Anomalia {

    @Id
    private int Id;
    private String Sensor;
    private int Zona;
    private double ValorAnomalo;
    private String TipoAnomalia;

    private String PayloadRecebido;
    private Date Hora;

    public Anomalia(String sensor, int zona, double valorAnomalo, String tipoAnomalia, String payloadRecebido, Date hora) {
        Sensor = sensor;
        Zona = zona;
        ValorAnomalo = valorAnomalo;
        TipoAnomalia = tipoAnomalia;
        PayloadRecebido = payloadRecebido;
        Hora = hora;
    }

    @Override
    public String toString() {
        return "Anomalia{" +
                "Id=" + Id +
                ", Sensor='" + Sensor + '\'' +
                ", Zona=" + Zona +
                ", ValorAnomalo=" + ValorAnomalo +
                ", TipoAnomalia='" + TipoAnomalia + '\'' +
                ", PayloadRecebido='" + PayloadRecebido + '\'' +
                ", Hora=" + Hora +
                '}';
    }
}