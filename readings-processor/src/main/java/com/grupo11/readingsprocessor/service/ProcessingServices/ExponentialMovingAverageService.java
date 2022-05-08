package com.grupo11.readingsprocessor.service.ProcessingServices;

import com.grupo11.readingsprocessor.database.models.EmaSensorAlpha;
import com.grupo11.readingsprocessor.database.models.SensorType;

public final class ExponentialMovingAverageService {
  private SensorType _sensorType;
  private String _sensorId;
  private double _alpha;
  private double _value = -1;

  public void tryReset(double value) {
    if (_value == -1) _value = value;
  }

  public void update(double value) {
    _value = (_alpha * (value)) + ((1 - _alpha) * _value);
  }

  public double get() {
    return _value;
  }

  public ExponentialMovingAverageService(SensorType sensorType, String sensorId) {
    System.out.println(
        String.format("Instantiating new ExponentialMovingAverageService service for %s", sensorId)
    );

    _sensorId = sensorId;
    _sensorType = sensorType;

    switch (_sensorType) {
      case Humidity:
        _alpha = EmaSensorAlpha.HUMIDITY;
        break;
      case Temperature:
        _alpha = EmaSensorAlpha.TEMPERATURE;
        break;
      case Luminosity:
        _alpha = EmaSensorAlpha.LUMINOSITY;
        break;
      default:
        throw new IllegalArgumentException("SensorType value is invalid");
    }

    System.out.println(sensorType + " alpha value set to " + _alpha);
  }
}
