package com.grupo11.readingsprocessor.mqtt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Topics {
  public static String Anomaly;
  public static String Reading;
  public static String WrongFormat;

  @Value("${broker.topic2}")
  public void setAnomaly(String value) {
    Anomaly = value;
  }

  @Value("${broker.topic1}")
  public void setReading(String value) {
    Reading = value;
  }

  @Value("${broker.topic3}")
  public void setWrongFormat(String value) {
    WrongFormat = value;
  }
}
