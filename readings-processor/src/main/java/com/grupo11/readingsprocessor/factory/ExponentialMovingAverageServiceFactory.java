package com.grupo11.readingsprocessor.factory;

import com.grupo11.readingsprocessor.database.models.SensorType;
import com.grupo11.readingsprocessor.service.ProcessingServices.ExponentialMovingAverageService;
import java.util.concurrent.ConcurrentHashMap;

public class ExponentialMovingAverageServiceFactory {
  private ConcurrentHashMap<String, ExponentialMovingAverageService> services;

  private static ExponentialMovingAverageServiceFactory instance = null;

  private ExponentialMovingAverageServiceFactory() {
    services = new ConcurrentHashMap<>();
  }

  public static synchronized ExponentialMovingAverageServiceFactory getInstance() {
    if (instance == null) {
      instance = new ExponentialMovingAverageServiceFactory();
    }

    return instance;
  }

  public ExponentialMovingAverageService getService(String sensorId) {
    if (services.containsKey(sensorId)) return services.get(sensorId);

    ExponentialMovingAverageService service
        = new ExponentialMovingAverageService(getSensorTypeFromSensorId(sensorId), sensorId);

    services.put(sensorId, service);
    return service;
  }

  private SensorType getSensorTypeFromSensorId(String sensorId) {
    if (sensorId.startsWith("T")) {
      return SensorType.Temperature;
    }
    else if (sensorId.startsWith("H")) {
      return SensorType.Humidity;
    }
    else return SensorType.Luminosity;
  }
}
