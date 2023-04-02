package com.sensorproject.common.dto;

import com.sensorproject.common.Constants;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MeasurementDTO implements Serializable {
    @Column(name = "value")
    @NotNull(message = Constants.VALUE_IS_EMPTY_ERROR_MESSAGE)
    @Min(value = -100, message = Constants.VALUE_IS_LESS_ERROR_MESSAGE)
    @Max(value = 100, message = Constants.VALUE_IS_GREATER_ERROR_MESSAGE)
    private Double value;


    @Column(name = "raining")
    @NotNull(message = Constants.RAINING_IS_EMPTY_ERROR_MESSAGE)
    private Boolean raining;

    @ManyToOne
    @NotNull(message = Constants.SENSOR_IS_EMPTY_ERROR_MESSAGE)
    private SensorDTO sensor;

    public MeasurementDTO(double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
