package org.grupo11.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Medicao implements Serializable, Measurements {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    @Id
    private int Id;
    private int Zona;
    private String Sensor;
    private LocalDateTime DataHora;
    private double Leitura;
    private LocalDateTime DataHoraObjectId;

    @Override
    public String getIdSensor() {
        return this.Sensor;
    }

    @Override
    public double getMeasurement() {
        return this.Leitura;
    }

    public int getZona(){
        return this.Zona;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return this.DataHora;
    }
}
