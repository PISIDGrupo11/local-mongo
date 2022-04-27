package com.grupo11.readingsprocessor.database.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Anomalia {

    @Id
    private int Id;
    private String Sensor;
    private int Zona;
    private double ValorAnomalo;
    private String TipoAnomalia;
    private String ValorRecebido;
    private Date Hora;
}
