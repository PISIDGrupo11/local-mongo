package com.grupo11.readingsprocessor.database.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Anomalia {

    @Id
    private int Id;
    private String IDSensor;
    private double ValorAnomalo;
    private String TipoAnomalia;
    private Date Hora;
}
