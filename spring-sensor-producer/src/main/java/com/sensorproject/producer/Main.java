package com.sensorproject.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensorproject.common.Constants;
import com.sensorproject.common.dto.MeasurementDTO;
import com.sensorproject.common.dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {
     private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) {
        registerNewSensor();
        System.out.println("New Sensor registered");
        Random random = new Random();
        for (int i = 0; i <= 1000; i++) {
            sendMeasurement(random.nextDouble() * 50, random.nextBoolean());
        }
        System.out.println("Dummy data saved");
        receiveAllMeasurements();

    }

    private static void receiveAllMeasurements() {
        MeasurementDTO[] jsonResponse = restTemplate.getForObject(Constants.URL_TO_GET_ALL_MEASUREMENT, MeasurementDTO[].class);
        if (jsonResponse != null) {
            Arrays.asList(jsonResponse).forEach(System.out::println);
        }
    }

    private static void sendMeasurement(double value, boolean rainy) {
        SensorDTO sensorDTO = new SensorDTO(Constants.NEW_SENSOR_NAME);
        MeasurementDTO measurementDTO = new MeasurementDTO(value, rainy, sensorDTO);
        try {
            String jsonStr = objectMapper.writeValueAsString(measurementDTO);
            HttpEntity<String> entity = new HttpEntity<>(jsonStr, getHttpHeaders());
            restTemplate.exchange(Constants.URL_TO_ADD_MEASUREMENT, HttpMethod.POST, entity, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(Constants.HEADER_NAME, Constants.HEADER_VALUE);
        return headers;
    }

    private static void registerNewSensor() {
        SensorDTO sensorDTO = new SensorDTO(Constants.NEW_SENSOR_NAME);
        try {
            String jsonStr = objectMapper.writeValueAsString(sensorDTO);
            HttpEntity<String> entity = new HttpEntity<>(jsonStr, getHttpHeaders());
            restTemplate.exchange(Constants.URL_TO_REGISTER_NEW_SENSOR, HttpMethod.POST, entity, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}