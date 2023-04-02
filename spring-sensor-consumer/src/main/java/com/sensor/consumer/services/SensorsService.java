package com.sensor.consumer.services;

import com.sensor.consumer.exceptions.SensorNotFoundException;
import com.sensor.consumer.model.Sensor;
import com.sensor.consumer.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional(readOnly = false)
public class SensorsService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorsService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor findSensorById(int id) {
        return sensorRepository.findById(id).orElseThrow(SensorNotFoundException::new);
    }

    @Transactional(readOnly = false)
    public void registerSensor(Sensor sensor) {
        sensorRepository.save(enrichSensor(sensor));
    }

    public Collection<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor findSensorByName(String name) {
        return sensorRepository.findByName(name).stream().findAny().orElseThrow(SensorNotFoundException::new);
    }

    public Optional<Sensor> findOptionalSensorByName(String name) {
        return sensorRepository.findByName(name).stream().findAny();
    }
    private Sensor enrichSensor(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
        return sensor;
    }

}
