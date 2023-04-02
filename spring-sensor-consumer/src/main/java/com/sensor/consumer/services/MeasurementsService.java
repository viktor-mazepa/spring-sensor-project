package com.sensor.consumer.services;

import com.sensor.consumer.model.Measurement;
import com.sensor.consumer.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementsService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional(readOnly = false)
    public void saveMeasurement(Measurement measurement) {
        measurementRepository.save(enrichMeasurement(measurement));
    }

    public Collection<Measurement> findAllMeasurements() {
        return measurementRepository.findAll();
    }

    private Measurement enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        return measurement;
    }

    public Long getRainyDaysCount() {
        return measurementRepository.getRainyDaysCount();
    }
}
