# The IT Solutions Use Case – Energy Consumption

## Introduction
* In this repository I present my solution to the [Energy Consumption Use Case](./requirements/use-case.pdf).
To fulfill the requirements I used Docker, the [docker-compose.yml](./docker-compose.yml) file contains all the necessary properties to be able to create my services: 
A service with my Java 11 based Spring Boot (2.1.8.RELEASE) application, and a service for a PostgreSQL database (15.0 version). 
* According to the requirements, the database will be initialized with some valid sample data during the startup process.
The [import.sql](./src/main/resources/import.sql) file contains the scripts for the initialization.
* The application POST endpoints consume data as JSON payloads, in the [sample-requests](./sample-requests) folder there are samples to demonstrate the correct format. 
I created these JSON objects to be similar to the legacy data formats, but now they are more compact and optimized to process.
The GET endpoints produce response objects in the presented format that you can check in the [sample-responses](./sample-responses) folder.
* The application uses the default 8080 port.
* In the [code-coverage-report](./code-coverage-report) folder there is a generated [report](./code-coverage-report/index.html) about the code coverage of the project.

## API endpoints
Here you can see a short introduction about the created endpoints with their responses, and response codes.

### `GET api/v1/profiles`
* **200 OK**, returns with the stored profiles.

### `GET api/v1/profiles/{profileName}`
* **200 OK**, returns the specific profile based on the profile name.
* Returns with 400 Bad request, if the profile cannot be found.

### `POST api/v1/profiles`
* It accepts JSON payload that represents profile(s), validates and saves them into the database.
* **201 Created**, if profile(s) are validated and saved.
* **400 Bad request**, if the profile's fractions are invalid based on the requirements.
* **400 Bad request**, if the profile already exists (based on the name).

### `GET api/v1/meter-readings/{id}`
* **200 OK**, returns with a MeterResponse object, that contains the meter readings and the calculated consumption for each month.
* **400 Bad request**, if the given id does not exist.

### `GET api/v1/meter-readings/{id}/{month}`
* **200 OK**, returns with a simplified MeterResponse object that contains meter reading and consumption for the given month.
* The month path variable should be in a specific format, like: (JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC).
* **400 Bad request**, if the given id does not exist.

### `POST api/v1/meter-readings`
* It accepts JSON payload that represents MeterRequest objects, validates and saves them into the database.
* **201 Created**, if request(s) are validated and saved.
* **400 Bad request**, if the mentioned profile does not exist.
* **400 Bad request**, if the meterId already exists in the database.
* **400 Bad request**, if the meter readings are invalid based on the requirements.

## How to start the project
Please perform these commands to build the application and start the containers.
1. `mvn clean package`
2. `docker compose up`
