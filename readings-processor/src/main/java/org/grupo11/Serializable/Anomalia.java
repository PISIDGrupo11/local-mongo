package org.grupo11.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
public class Anomalia implements Serializable, Measurements {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    @Id
    private int Id;
    private String IDSensor;
    private double ValorAnomalo;
    private String TipoAnomalia;
    private LocalDateTime Hora;


    @Override
    public String toString(){
        return String.format("Id: %d\nIdSensor: %s\nValorAnomalo: %f\nTipoAnomalia %s",
                this.Id,
                this.IDSensor,
                this.ValorAnomalo,
                this.TipoAnomalia);

    }

    @Override
    public String getIdSensor() {
        return this.IDSensor;
    }

    @Override
    public double getMeasurement() {
        return this.ValorAnomalo;
    }


    public String getAnomalyType() {
        return this.TipoAnomalia;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return this.Hora;
    }
}
