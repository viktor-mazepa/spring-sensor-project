package com.sensor.consumer.conrollers;

import com.sensorproject.common.dto.SensorDTO;
import com.sensor.consumer.exceptions.SensorRegistrationException;
import com.sensor.consumer.model.Sensor;
import com.sensor.consumer.services.SensorsService;
import com.sensor.consumer.utils.ErrorResponse;
import com.sensor.consumer.utils.validators.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;


    @Autowired
    public SensorController(SensorsService sensorsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();

            Collection<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(fieldError -> message.append(fieldError.getField()).append("-")
                    .append(fieldError.getDefaultMessage())
                    .append(";"));
            throw new SensorRegistrationException(message.toString());
        }
        sensorsService.registerSensor(convertToSensorEntity(sensorDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Sensor convertToSensorEntity(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleCreationException(SensorRegistrationException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
