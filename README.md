<h1>Spring Cource. Practical Project 3</h1>
Repository for code which I wrote during Spring Course study (https://www.udemy.com/course/spring-alishev/).
REST application which recieves measurement information from abstaract sensors (tmperature, rainy flag, sensor name) and store to the database.
Also it provides possibility to get list of all mesurements in JSON format, register new sensor, get count of rainy days from database.
The application implements OneToMany relations between Sensor and Measurements in the database and incomming data validation.Project containes three modules:
- ```spring-sensor-common``` - common module with constants and DTO classes

