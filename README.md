# Spring Course. Practical Project 3
Repository for code which I wrote during Spring Course study (https://www.udemy.com/course/spring-alishev/).
REST application which receives measurement information from abstract sensors (temperature rainy flag, sensor name) and stores to the database.
Also, it provides the possibility to get a list of all measurements in JSON format, register new sensors, get the count of rainy days from the database.
The application implements OneToMany relations between Sensor and Measurements in the database and incoming data validation. Project contains three modules:
- ```spring-sensor-common``` - common module with constants and DTO classes
- ```spring-sensor-consumer``` - REST application that received information from the abstract sensor, stores it to DB, and provides all needful information in JSON format
- ```spring-sensor-producer``` - a simple module that can send the request to create a new sensor and 1000 requests with random measurements

The application provides next endpoints:
- ```/sensors/registration``` - endpoint for sensor registration (allow POST request)
- ```/measurements/add``` - endpoint for measurement add (allow POST requests)
- ```/measurements``` - returns a list of all measurements from the database in JSON format (allow GET request)
- ```/measurements/rainyDaysCount``` - returns count of rainy days from database

Example of requests to ```/sensors/registration``` endpoint:
```
{
    "name":"Test Sensor"
}
```
Example of requests to ```/measurements/add``` endpoint:
```
{
    "value":31.1,
    "raining":true,
    "sensor":{
        "name":"Test Sensor"
    }
}
```

Example of response for GET requests to ```/measurements``` endpoint:
```
[
    {
        "value": 23.1980348497852,
        "raining": true,
        "sensor": {
            "name": "Producer Sensor"
        }
    },
    {
        "value": 22.2994809287067,
        "raining": true,
        "sensor": {
            "name": "Producer Sensor"
        }
    },
    {
        "value": 15.821137646021,
        "raining": true,
        "sensor": {
            "name": "Producer Sensor"
        }
    }
]
```
Example of response for GET requests to ```/measurements/rainyDaysCount``` endpoint:
```
{
    "rainyDaysCount": 484
}
```
The project was created with Spring Boot, ModelMapper, and Hibernate Validator. For database collaboration, it is using Hibernate+Spring JPA.
```spring-sensor-consumer``` using docker-compose to build application container based on tomcat-jre image and postgres image for database container. 
```spring-sensor-producer``` using jackson-databind and spring-web, module should be run locally.

