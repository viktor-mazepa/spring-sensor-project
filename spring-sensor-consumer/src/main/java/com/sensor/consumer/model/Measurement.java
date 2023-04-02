package com.sensor.consumer.model;

import com.sensorproject.common.Constants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "value")
    @NotNull (message = Constants.VALUE_IS_EMPTY_ERROR_MESSAGE)
    @Min(value = -100, message = Constants.VALUE_IS_LESS_ERROR_MESSAGE)
    @Max(value = 100, message = Constants.VALUE_IS_GREATER_ERROR_MESSAGE)
    private double value;


    @Column(name = "raining")
    @NotNull (message = Constants.RAINING_IS_EMPTY_ERROR_MESSAGE)
    private boolean raining;

    @ManyToOne
    @NotNull(message = Constants.SENSOR_IS_EMPTY_ERROR_MESSAGE)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement() {
    }

    public Measurement(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}