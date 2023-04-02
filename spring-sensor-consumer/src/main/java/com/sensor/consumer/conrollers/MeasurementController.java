package com.sensor.consumer.conrollers;

import com.sensorproject.common.dto.MeasurementDTO;
import com.sensorproject.common.dto.RainyDaysDTO;
import com.sensor.consumer.exceptions.MeasurementRegistrationException;
import com.sensor.consumer.exceptions.SensorNotFoundException;
import com.sensor.consumer.model.Measurement;
import com.sensor.consumer.model.Sensor;
import com.sensor.consumer.services.MeasurementsService;
import com.sensor.consumer.services.SensorsService;
import com.sensor.consumer.utils.ErrorResponse;
import com.sensorproject.common.Constants;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementsService measurementsService, SensorsService sensorsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();

            Collection<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(fieldError -> message.append(fieldError.getField()).append("-")
                    .append(fieldError.getDefaultMessage())
                    .append(";"));
            throw new MeasurementRegistrationException(message.toString());
        }

        Sensor sensor = sensorsService.findSensorByName(measurementDTO.getSensor().getName());
        Measurement measurement = convertToMeasurementEntity(measurementDTO);
        measurement.setSensor(sensor);
        measurementsService.saveMeasurement(measurement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public Collection<MeasurementDTO> getAllMeasurements() {
        return measurementsService.findAllMeasurements().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public RainyDaysDTO getRainyDaysCount() {
        return new RainyDaysDTO(measurementsService.getRainyDaysCount());
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleMeasurementCreationException(MeasurementRegistrationException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleSensorNotFoundException(SensorNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(Constants.SENSOR_NOT_FOUND_ERROR_MESSAGE, new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private Measurement convertToMeasurementEntity(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
