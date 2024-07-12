Trainer Tracker Backend is a Spring Boot application designed to manage trainers, 
clients, and exercises. The application allows trainers to add and manage their clients and exercises. 
This backend provides RESTful APIs for CRUD operations on these entities.

Technologies Used:
Java 17
Spring Boot 3.3.1
Spring Data JPA
MySQL
Lombok
JUnit 5
Maven

Getting Started:
Prerequisites
Java 17
Maven
MySQL

API Endpoints:
Trainer Endpoints
GET /api/trainers: Get all trainers
GET /api/trainers/{id}: Get a trainer by ID
POST /api/trainers: Add a new trainer
PUT /api/trainers/{id}: Update an existing trainer
DELETE /api/trainers/{id}: Delete a trainer by ID
GET /api/trainers/clients/{trainerId}: Get all clients of a specific trainer
GET /api/trainers/exercises/{trainerId}: Get all exercises of a specific trainer

Client Endpoints:
GET /api/clients: Get all clients
GET /api/clients/{id}: Get a client by ID
POST /api/clients: Add a new client
PATCH /api/clients/email/{id}: Update client's email
PATCH /api/clients/username/{id}: Update client's username
PATCH /api/clients/password/{id}: Update client's password
DELETE /api/clients/{id}: Delete a client by ID

Exercise Endpoints:
GET /api/exercises: Get all exercises
GET /api/exercises/{id}: Get an exercise by ID
POST /api/exercises: Add a new exercise
PUT /api/exercises/{id}: Update an existing exercise
DELETE /api/exercises/{id}: Delete an exercise by ID

License
This project is licensed under the Luietech License. See the LICENSE file for details ;-)
