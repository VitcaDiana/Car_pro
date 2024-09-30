
# Hi, I'm Diana! 👋

Here you can find the documentation of the Car_pro project
## 🚀 About Me
💻Dedicated back-end software developer | 👨‍💻Motivated to work for companies to build great back-ends | Java, Spring Boot | Passionate about solving problems using technology


## 🛠 Skills
Back-end development, Software development, Web development, Java, Spring framework, Spring boot, Data structures, Algorithms, OOP, MySQL, Relational databases, SQL, Git, HTML, CSS, Web services, Rest APIs, Unit Testing

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/VitcaDiana)


# Car Pro App
This application is designed for fleet managers and drivers, supporting two main user roles: managers and drivers.

**Fleet Managers** have the ability to supervise vehicles within the fleet, tracking key details such as real-time location, charging status, and other relevant vehicle data through integrated API. Managers can also manage fleet expenses and track important documentation, such as insurance and maintenance records. The system ensures that managers are notified via email before any document expires, enabling timely renewals and aggrement with regulations.

**Drivers** can be part of a fleet or operate independently. Drivers in the fleet can access vehicle details, including location and charging information.They can see the list of expenses, and if they are not part of the fleet, they will receive an email with the document expiration date.
## Features
**As a driver, I can:**

- add car
- view details for a car if it is ELECTRIC
- add documents
- receive an email when a document expires
- delete document
- view details for a document
- add an expense
- delete an expense
- view total expenses of a car per month and per year

**As a manager, I can:**
- add car
- add car to a driver
- delete driver from a car
- delete car
- view details for a car
- add documents
- receive an email when a document expires
- delete document
- view details for a document
- view statistics related to the expenses of:
  - a car
  - of the fleet
  - the total expenses of the fleet per month and per year
- add an expense
- delete an expense



## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Demo

You can view the demo here:



## API Reference

https://enode.com/

#### Add a new car

```http
   POST /car/${fleetId}
```

| Parameter | Type     | Description                                   |
| :-------- | :------- |:----------------------------------------------|
| `fleetId`    | `long`   | **Required**. The id of fleet. The required field is set to false, meaning this parameter is optional.           |
| `body`    | `json`   | **Required**. The car to be added             |

Request body example:

```json
{
"brand": "Tesla",
"model": "Model 3",
"productionYear": 2022,
"color": "Black",
"mileage": 5000,
"registrationNumber": "B123XYZ",
"carType": "ELECTRIC",
"vin": "1HGCM82633A123456"

}
```  

#### Add driver to the car

```http
  POST /car/{carId}/addCarToDriver/{driverId}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `carId`    | `long` | **Required**. The id of the car    | 
| `driverId` | `long` | **Required**. The id of the driver | 

#### View information about car

```http
   GET /car/{carId}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `carId`    | `long` | **Required**. The id of the car    |


#### Delete a car

```http
   DELETE /car/{id}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `id`    | `long` | **Required**. The id of the car    |

#### View all drivers by a car

```http
   GET /driver/{carId}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `carId`    | `long` | **Required**. The id of the car    |



#### Create a document

```http
   POST /document/{carId}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `carId`    | `long` | **Required**. The id of the car    |
| `body`    | `json`   | **Required**. The document to be added             |

Request body example:

```json
{
    "name":"asigurare",
    "expirationDate": "2024-10-17",
    "startDate": "2024-09-15"
    
}
```  
#### View document
```http
   GET /document/{documentId}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `id`    | `long` | **Required**. The id of the document  

#### Delete a document
```http
   DELETE /document/{id}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `id`    | `long` | **Required**. The id of the document    |


#### Create a fleet

```http
   POST /fleet/create/{managerId}/
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `managerId`    | `long` | **Required**. The id of the manager   |
| `body`    | `json`   | **Required**. The fleet to be added             |

Request body example:

```json
{
    "fleetName": "Fleet1"
    
}
```

#### Create an expense

```http
   POST /expenses/add/{carId}/
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `carId`    | `long` | **Required**. The id of the car   |
| `body`    | `json`   | **Required**. The expense to be added             |

Request body example:

```json
{
    "name":"schimb anvelope",
    "price":"1000",
    "date": "2024-04-30"
}
```

#### View expenses by a car

```http
   GET /expenses/car/{carId}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `carId`    | `long` | **Required**. The id of the car   |


#### View expenses by a fleet

```http
   GET /expenses/fleet/{fleetId}
```

| Parameter  | Type   | Description                        |
|:-----------|:-------|:-----------------------------------|
| `fleetId`    | `long` | **Required**. The id of the fleet   |

## API Authentication and Authorization

There are only two requests which don't require authorization headers.

#### Authenticate (login)

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to authenticate  |

Request body example:

```json
{
  "username": "string",
  "password": "string"
}
```  

#### Register

```http
  POST /register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to register  |

Request body example:

```json
{
  "username": "string",
  "password": "string"
}

```  
After running the authenticate request, the user will obtain an access token that will be used in all subsequent request in order to authenticate the user and to authorize the user based on its role.

This is an example of what should be included in the request header:

```http
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMTQzMzEyfQ.dxIzsD9Bm8y_kw3MOoZ2JXIKOg--uZaA5XNtBLdGYc4Ps3nlzBFDwBJi0bEeHlCggonZ6nQ2zwCI0D5a7dXjmw
```  


## Database model
[![carpro-db5.png](https://i.postimg.cc/5Nb2hdjf/carpro-db5.png)](https://postimg.cc/7bt4zRgR)

## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For building and running the application with Docker, you need:

- Docker Desktop (for Windows and Mac) or Docker Engine (for Linux)
- Docker Compose (optional, for orchestrating multi-container applications)

## Dependencies

You don't need any additional dependencies.
All dependecies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.
## Installation

Clone the project

```bash
  git clone https://github.com/VitcaDiana/CarPro_app
```

Go to the project directory

```bash
  cd my-project
```

## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Running Locally with Docker

### Build the Docker Image

Navigate to the root directory of your project where the Dockerfile is located, and run the following command:

```http
docker build -t crm-app .
```

### Run the Application

```http
docker run -p 8080:8080 crm-app
```

### Run using Docker Compose (Optional)

If your application requires running multiple services (like an app server and a database), you can use Docker Compose to manage these services. Here is an example docker-compose.yml file for your application:

```http
docker-compose up
```

This command builds and starts both the application and the database containers. The application will connect to the MySQL database as configured in your application's properties.

## Deployment with Docker
Deploying your dockerized application can vary based on your hosting provider. Typically, you would push your Docker image to a container registry (e.g., Docker Hub, GitHub Container Registry) and then pull and run it on your production server. Here are the basic steps for pushing to Docker Hub:

### Tag your image
```http
docker tag CarPro-app yourusername/crm-app:latest
```

### Push your image to the registry
```http
docker push yourusername/CarPro-app:latest
```

After pushing your image, you can follow your hosting provider instructions.



## Roadmap

In the future, application can be extended with following:

- Implement SMS reminders for vehicle maintenance schedules for expiration document
- Integrate an AI chatbot for customer support, capable of answering frequently asked questions
- Include a feature for optimizing routes based on traffic conditions


## Badges


![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
