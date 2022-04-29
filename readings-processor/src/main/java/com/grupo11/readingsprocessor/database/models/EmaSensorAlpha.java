package com.grupo11.readingsprocessor.database.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmaSensorAlpha {
  public static double TEMPERATURE;
  public static double HUMIDITY;
  public static double LUMINOSITY;

  @Value("${application-logic.ema-parameters.alphas.temperature}")
  public void setTemperatureAlpha(double alpha) {
    TEMPERATURE = alpha;
  }

  @Value("${application-logic.ema-parameters.alphas.luminosity}")
  public void setLuminosityAlpha(double alpha) {
    LUMINOSITY = alpha;
  }

  @Value("${application-logic.ema-parameters.alphas.humidity}")
  public void setHumidityAlpha(double alpha) {
    HUMIDITY = alpha;
  }
}
