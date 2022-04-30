package com.grupo11.readingsprocessor.service;

import com.grupo11.readingsprocessor.FilterSensorData;
import com.grupo11.readingsprocessor.database.models.EmaSensorAlpha;
import com.grupo11.readingsprocessor.database.models.SensorData;
import com.grupo11.readingsprocessor.database.models.SensorDataClassification;
import com.grupo11.readingsprocessor.database.models.SensorType;
import com.grupo11.readingsprocessor.database.models.WeAreYourEnemy;
import com.grupo11.readingsprocessor.database.repository.LocalMongoDBRepository;
import com.grupo11.readingsprocessor.mqtt.Topics;
import com.grupo11.readingsprocessor.service.usecases.ManufacturingErrorDetection;
import java.util.HashMap;
import java.util.Hashtable;
import org.springframework.stereotype.Service;

@Service
public class ReadingsClassifierService {
  private final ManufacturingErrorDetection manufacturingErrorDetection;
  private HashMap<String, Double> LastValues;

  public ReadingsClassifierService(ManufacturingErrorDetection manufacturingErrorDetection) {
    this.manufacturingErrorDetection = manufacturingErrorDetection;

    LastValues = new HashMap<>();
  }

  public FilterSensorData getClassifiedReading(
      SensorData sensorData, HashMap<String, Hashtable<String, Double>> factoryInformationMap) {

    var filteredSensorData = manufacturingErrorDetection.execute(sensorData, factoryInformationMap);

    if (filteredSensorData.getClassification().equals(SensorDataClassification.NormalMeasurement)) {
      // Calculates the difference percentage between the last value sent (raw)
      // and the current value being checked for
      var currentReadingValue = filteredSensorData.getSensorData().getMedicao();
      var sensorId = filteredSensorData.getSensorData().getSensor();

      if (!LastValues.containsKey(sensorId)) {
        LastValues.put(sensorId, currentReadingValue);
        return filteredSensorData;
      }

      var percentDifference = calcPercentDifference(LastValues.get(sensorId), currentReadingValue);

      // If the percentage is over a defined threshold, we classify it as `OverThreshold`,
      // which is ultimately an anomaly type
      var threshold = getPeakDetectionThreshold(
          getSensorTypeFromSensorId(filteredSensorData.getSensorData().getSensor()));

      if (percentDifference > threshold) {
        System.out.println("Found peak for reading: Threshold " + threshold + " read " + percentDifference);
        filteredSensorData.setClassification(SensorDataClassification.OverThreshold);
        filteredSensorData.setMqttTopic(Topics.Anomaly);
      }

      LastValues.replace(sensorId, LastValues.get(sensorId), currentReadingValue);
    }

    return filteredSensorData;
  }

  private double calcPercentDifference(double v1, double v2) {
    return Math.abs(v1 - v2) / ((v1 + v2) / 2);
  }

  private double getPeakDetectionThreshold(SensorType sensorType) {
    switch (sensorType) {
      case Humidity:
        return WeAreYourEnemy.HUMIDITY;
      case Temperature:
        return WeAreYourEnemy.TEMPERATURE;
      case Luminosity:
        return WeAreYourEnemy.LUMINOSITY;
      default:
        throw new IllegalArgumentException("sensorId value is invalid");
    }
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
