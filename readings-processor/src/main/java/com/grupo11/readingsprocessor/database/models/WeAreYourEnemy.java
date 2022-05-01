package com.grupo11.readingsprocessor.database.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeAreYourEnemy {
  public static double TEMPERATURE;
  public static double HUMIDITY;
  public static double LUMINOSITY;

  @Value("${application-logic.ema-parameters.peak-detection-thresholds.temperature}")
  public void setTemperatureAlpha(double alpha) {
    TEMPERATURE = alpha;
  }

  @Value("${application-logic.ema-parameters.peak-detection-thresholds.luminosity}")
  public void setLuminosityAlpha(double alpha) {
    LUMINOSITY = alpha;
  }

  @Value("${application-logic.ema-parameters.peak-detection-thresholds.humidity}")
  public void setHumidityAlpha(double alpha) {
    HUMIDITY = alpha;
  }
}
