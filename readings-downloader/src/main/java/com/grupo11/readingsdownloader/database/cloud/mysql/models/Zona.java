package com.grupo11.readingsdownloader.database.cloud.mysql.models;

import lombok.Data;

@Data
public class Zona {

    private final int idZona;
    private final float temperatura;
    private final float humidade;
    private final float luz;
}
