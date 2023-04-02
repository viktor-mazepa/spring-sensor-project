# Spring Cource. Practical Project 3
Repository for code which I wrote during Spring Course study (https://www.udemy.com/course/spring-alishev/).
REST application which recieves measurement information from abstaract sensors (tmperature, rainy flag, sensor name) and store to the database.
Also it provides possibility to get list of all mesurements in JSON format, register new sensor, get count of rainy days from database.
The application implements OneToMany relations between Sensor and Measurements in the database and incomming data validation.Project containes three modules:
- ```spring-sensor-common``` - common module with constants and DTO classes
- ```spring-sensor-consumer``` - REST application that received information from abstract sensor, store it to DB and provides all needfull information in JSON format
- ```spring-sensor-producer``` - simple module that can send request to create new sensor and 1000 requests with random measurements

Application provides next endpoints:
- ```/sensors/registration``` - endpoit for sensor registration (allow POST request)
- ```/measurements/add``` - endpoit for measurement add (allow POST requsts)
- ```/measurements``` - returns list of all measurements from database in JSON format (allow GET request)
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
```spring-sensor-producer``` using jackson-databind and spring-web, module should be run localy.

