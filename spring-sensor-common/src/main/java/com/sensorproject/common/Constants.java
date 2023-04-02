package com.sensorproject.common;

public interface Constants {
    String VALUE_IS_EMPTY_ERROR_MESSAGE = "Value of Measurement should not be null";
    String VALUE_IS_GREATER_ERROR_MESSAGE = "Value should be less than 100";
    String VALUE_IS_LESS_ERROR_MESSAGE = "Value should be greater than -100";
    String SENSOR_ALREADY_EXISTS_MESSAGE = "Sensor with name already exists";

    String RAINING_IS_EMPTY_ERROR_MESSAGE = "Raining parameter should not be null";
    String SENSOR_IS_EMPTY_ERROR_MESSAGE = "Sensor should not be null";
    String SENSOR_NAME_IS_EMPTY_ERROR_MESSAGE = "Sensor name should not be null";
    String SENSOR_NOT_FOUND_ERROR_MESSAGE = "Sensor with name not found";

    String URL_TO_REGISTER_NEW_SENSOR = "http://localhost:8080/sensors/registration";
    String URL_TO_ADD_MEASUREMENT = "http://localhost:8080/measurements/add";
    String URL_TO_GET_ALL_MEASUREMENT = "http://localhost:8080/measurements";
    String NEW_SENSOR_NAME = "Producer Sensor";
    String HEADER_NAME = "user-agent";
    String HEADER_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

}
