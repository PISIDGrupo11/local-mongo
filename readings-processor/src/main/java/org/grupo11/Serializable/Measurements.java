package org.grupo11.Serializable;

import java.time.LocalDateTime;

public interface Measurements {
    
    String getIdSensor();
    double getMeasurement();
    LocalDateTime getTimestamp();
    
    
}
