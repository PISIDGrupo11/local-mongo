package com.grupo11.readingsprocessor.database.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeAreYourEnemy {
  public static double TEMPERATURE;
  public static double HUMIDITY;
  public static double LUMINOSITY;

  public static int TEMPERATURE_RUNNING_COUNTER;
  public static int HUMIDITY_RUNNING_COUNTER;
  public static int LUMINOSITY_RUNNING_COUNTER;

  @Value("${application-logic.ema-parameters.peak-detection-anomaly-counter-trigger.temperature}")
  public void setTemperatureCounter(int val) {
    TEMPERATURE_RUNNING_COUNTER = val;
  }

  @Value("${application-logic.ema-parameters.peak-detection-anomaly-counter-trigger.humidity}")
  public void setHumidityCounter(int val) {
    HUMIDITY_RUNNING_COUNTER = val;
  }

  @Value("${application-logic.ema-parameters.peak-detection-anomaly-counter-trigger.luminosity}")
  public void setLuminosityCounter(int val) {
    LUMINOSITY_RUNNING_COUNTER = val;
  }

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
