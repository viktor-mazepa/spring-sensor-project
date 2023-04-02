package com.sensorproject.common.dto;

import java.io.Serializable;

public class RainyDaysDTO implements Serializable
{
    Long rainyDaysCount;

    public RainyDaysDTO() {
    }

    public RainyDaysDTO(Long rainyDaysCount) {
        this.rainyDaysCount = rainyDaysCount;
    }

    public Long getRainyDaysCount() {
        return rainyDaysCount;
    }

    public void setRainyDaysCount(Long rainyDaysCount) {
        this.rainyDaysCount = rainyDaysCount;
    }
}
