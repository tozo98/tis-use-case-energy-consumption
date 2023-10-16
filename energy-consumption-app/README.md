# tis-use-case-energy-consumption

## About my solution
* In the sample-request folder I added some example request to demonstrate the new data format.
  These are a bit optimized for data processing, compared to the legacy data formats that were given in the description.
* I created a Docker-based solution, I placed all the necessary properties into the docker-compose.yml file to create my services: 
A service for the Java 11 based Spring Boot (2.1.8.RELEASE) application, and a service for the PostgreSQL database (15.0 version). 
* To make the evaluation easier, the database will be initialized with some valid sample data during the startup process.
The source/main/resources/import.sql file contains the scripts for the initialization.
* The application uses the default 8080 port.
* In the code-coverage-report folder you can find a generated report about the code coverage of the project.

## API endpoints

### `GET api/v1/profiles`

*   returns with the stored profiles

### `GET api/v1/profiles/{profileName}`

* returns the specific profile with the matching profile name.
* returns with 400 Bad request if the profile doesn't exist in the database.

### `POST api/v1/profiles`

### `GET api/v1/meter-readings/{id}`
### `GET api/v1/meter-readings/{id}/{month}`
### `POST api/v1/meter-readings`


## How to start the project
Please perform these commands to build the application and start the containers.
1. `mvn clean package`
2. `docker compose up`
