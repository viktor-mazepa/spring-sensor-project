package com.sensor.consumer.repositories;

import com.sensor.consumer.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Collection<Sensor> findByName(String name);
}
