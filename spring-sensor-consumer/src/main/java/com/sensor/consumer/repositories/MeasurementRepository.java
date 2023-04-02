package com.sensor.consumer.repositories;

import com.sensor.consumer.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    @Query(
            value = "SELECT count(1) FROM measurement m WHERE m.raining = true",
            nativeQuery = true)
    Long getRainyDaysCount();
}
