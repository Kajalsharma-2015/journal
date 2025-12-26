# Journal Management System

The Journal Management System is a backend application developed using Spring Boot as part of a summer training project.  
It allows users to securely create, manage, update, and delete personal journal entries through RESTful APIs.

---

## Features
- User registration with encrypted password storage
- Authentication and authorization using Spring Security
- Create, read, update, and delete journal entries
- MongoDB used for document-based data storage
- REST APIs tested using Postman

---

## Technologies Used
- Java
- Spring Boot
- Spring Security
- MongoDB
- Maven
- Postman

---

## Project Architecture
- Controller Layer – Handles API requests
- Service Layer – Contains business logic
- Repository Layer – Communicates with MongoDB
- Security Layer – Handles authentication and authorization

---

## Screenshots
![Create User API](screenshorts/Screenshot 2025-12-26 002353.pdf)  
![Journal API](screenshots/journal-api.png)

---

## How to Run the Project
1. Clone the repository  
2. Update MongoDB connection URI in `application.properties`  
3. Run the application using:
