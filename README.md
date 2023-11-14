# Doubt_Share_App-Revely-io_Assignment-

## Overview

The Doubt Sharing Application is designed to facilitate communication between students and tutors for resolving academic doubts. The application allows students to submit their doubts, which can be assigned to available tutors. Tutors can then resolve the doubts, creating a collaborative learning environment.

## Tech Stacks

- Spring Boot
- Spring Framework
- Spring Data JPA
- Spring Security
- JSON Web Token (JWT)
- MySQL
- Hibernate
- Java
- Lombok
- React (Frontend)
- HTML, CSS
- React Router DOM


## Modules

- Student Module
- Tutor Module
- Doubt Module
- Authentication Module

## Installation & Run

1. Clone the repository to your local system.
2. Update the database configuration in the `application.properties` file.
3. Create a database with the name "DoubtHandlingDB" in MySQL.
4. Run the application.

# Example Database Configuration
server.port=8085

# Database specific properties
spring.datasource.url=jdbc:mysql://localhost:3306/DoubtHandlingDB
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

# Doubt Sharing Application

## API Endpoints

### Student URLs

- **POST /doubt-sharing-app/student/add-doubt-request**: Add a doubt request.
- **POST /doubt-sharing-app/student/assign-doubt-to-live-tutor**: Assign a doubt to an available tutor.
- **GET /doubt-sharing-app/student/all-doubts/history**: Get the history of doubt requests.

### Tutor URLs

- **POST /doubt-sharing-app/tutor/doubt-solve/{doubtId}**: Resolve a doubt as a tutor.
- **GET /doubt-sharing-app/tutor/pending-doubts**: Get pending doubts for a tutor.

### Authentication URLs

- **POST /doubt-sharing-app/auth/register-student**: Register a student.
- **POST /doubt-sharing-app/auth/register-teacher**: Register a tutor.
- **GET /doubt-sharing-app/auth/user/signIn**: Sign in a user.
- **GET /doubt-sharing-app/auth/user/signOut**: Sign out a user.

## Authentication and Authorization

The application uses JSON Web Token (JWT) for authentication and authorization. Select Basic Authentication in the Authorization settings when making requests. Provide the username and password used during registration. The system will respond with a JWT token in the Headers section labeled as "Authorization." Retain this JWT token for further requests.

## User Registration Examples

### Student Registration

{
  "userName": "Sakshi",
  "email": "sakshi0704@gmail.com",
  "password": "12345",
  "userLanguage": "HINDI",
  "classGrade": "1"
}

### Tutor Registration
{
  "userName": "Ravi",
  "email": "ravi0704@gmail.com",
  "password": "12345",
  "userLanguage": "HINDI",
  "minGrade": "1",
  "maxGrade": "10",
  "subjectExpertise": "ENGLISH"
}

Thank you for exploring our Doubt Sharing Application!
