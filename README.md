
<h1 align="center" style="font-weight: bold;">FitTracker (server) 💻</h1>

<p align="center">
<a href="#tech">Technologies</a>
<a href="#started">Getting Started</a>
<a href="#colab">Collaborators</a>
<a href="#contribute">Contribute</a> 
</p>


<p align="center">Trainer Tracker Backend is a Spring Boot application designed to manage trainers, clients, and exercises. The application allows trainers to add and manage their clients and exercises. This backend provides RESTful APIs for CRUD operations on these entities.</p>


<p align="center">
<a href="https://github.com/LuieTech/FitTracker-server">📱 Visit this Project</a>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java 17
- Spring Boot 3.3.1
- Spring Data
- JPA
- MySQL
- Lombok
- JUnit 5
- Maven
- IntelliJ

<h2 id="started">🚀 Getting started</h2>

Prerequisites Java 17 Maven MySQL

API Endpoints: "/exercises"

Trainer Endpoints: 
GET "/trainers/"+trainerId: Get a trainer by ID 

Client Endpoints: 
GET `/trainers/clients/`+trainerId`: Get all clients of a specific trainer id 
GET "/clients/"+clientId: Get a specific client by Id 
POST '/clients': create a client
DELETE "/clients/"+clientId: Delete a client by its id.
PUT `/clients/${id}/comment: update a comment in a client profile 

Exercise Endpoints:
GET "/clients/exercises/"+clientId: to get all exercises of a specific client
POST "/exercises": to add an exercise to a client's list 
DELETE "/exercises/"+id: to delete an exercise from a client's list


<h3>Cloning</h3>

How to clone your project

```bash
git clone https://github.com/LuieTech/FitTracker-server.git
```

<h3>Starting</h3>

How to start your project

```bash
cd project-name
some-command-to-run it
```

<h2 id="contribute">📫 Contribute</h2>

Here you will explain how other developers can contribute to your project. For example, explaining how can create their branches, which patterns to follow and how to open an pull request

1. `git clone https://github.com/LuieTech/FitTracker-server.git
2. `git checkout -b feature/NAME`
3. Follow commit patterns
4. Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
