package com.grupo11.readingsdownloader.database.cloud.mysql.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Sensor {

    @Id
    private final int id;
    private final String tipo;
    private final float limiteInferior;
    private final float limiteSuperior;
    private final int idZone;

}
