package org.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Medicao {
    @Id
    private int Id;
    private int Zona;
    private String Sensor;
    private LocalDateTime DataHora;
    private double Leitura;
    private LocalDateTime DataHoraObjectId;
}
