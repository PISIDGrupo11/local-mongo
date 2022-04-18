package org.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Anomalia{

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
}
