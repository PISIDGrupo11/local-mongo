package com.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

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


    public Medicao(int zona, String sensor, double leitura, Date dataHoraObjectId) {
        this.Zona = zona;
        this.Sensor = sensor;
        this.Leitura = leitura;
        this.DataHoraObjectId = dataHoraObjectId;
        this.DataHora = new Date(System.currentTimeMillis());
    }

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
