package com.sensorproject.common.dto;

import com.sensorproject.common.Constants;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class SensorDTO implements Serializable {
    @Column(name = "name")
    @NotEmpty(message = Constants.SENSOR_NAME_IS_EMPTY_ERROR_MESSAGE)
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
