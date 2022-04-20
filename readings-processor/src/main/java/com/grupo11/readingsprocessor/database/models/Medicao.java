package com.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class Medicao {

    @Id
    private int Id;
    private int Zona;
    private String Sensor;
    private Date DataHora;
    private double Leitura;
    private Date DataHoraObjectId;


    @Override
    public String toString() {
        return "Medicao{" +
                "Id=" + Id +
                ", Zona=" + Zona +
                ", Sensor='" + Sensor + '\'' +
                ", DataHora=" + DataHora +
                ", Leitura=" + Leitura +
                ", DataHoraObjectId=" + DataHoraObjectId +
                '}';
    }
}
