package com.sensor.consumer.utils.validators;

import com.sensorproject.common.dto.SensorDTO;
import com.sensor.consumer.model.Sensor;
import com.sensor.consumer.services.SensorsService;
import com.sensorproject.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    private static final String rejectKey = "Name";

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SensorDTO sensor = (SensorDTO) o;
        if (sensorsService.findOptionalSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue(rejectKey, "", Constants.SENSOR_ALREADY_EXISTS_MESSAGE);
        }
    }
}
