# Ninja Registration API

The Ninja Registration API is a Spring Boot application built as a hands-on project to demonstrate the core fundamentals of RESTful API development.

---

## Project Purpose
This repository is an **educational resource** designed for learning purposes. It is not intended for production environments. The primary goal of this project is to showcase how to structure a Java backend application, implement security layers via DTOs, and handle data persistence using Spring Data JPA.

Key learning objectives included:
* Project layering (Controller, Service, Repository).
* Request validation and error handling.
* Understanding the lifecycle of an HTTP request.

---

## Core Features

* **DTO Pattern:** Implements Data Transfer Objects to decouple internal entities from the API layer, ensuring data integrity and security.
* **CRUD Operations:** Full support for creating, reading, updating, and deleting ninja profiles.
* **Data Validation:** Utilizes Jakarta Bean Validation to enforce data constraints and business rules.
* **In-Memory Database:** Configured with H2 for a seamless development and testing environment.

---

## API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | /ninjas/registerninja | Registers a new ninja. |
| GET | /ninjas/listninjas | Retrieves a list of all registered ninjas. |
| PUT | /ninjas/{id} | Updates information for an existing ninja. |
| DELETE | /ninjas/{id} | Removes a ninja record from the system. |

### Request Example (POST)
```json
{
  "name": "Ninja Tester",
  "email": "ninja@ninja.com",
  "birthDate": "1997-10-10",
  "category": "MEDIC",
  "ability": "NINJUTSU",
  "elementals": "WIND",
  "rank": "PRO",
  "description": "Aspiring Hokage"
}
```
## Gettind Started

**Prerequisites:**
- Java 21 or higher (I don't recommend Java 25)
- Maven 3.x
- A preferred IDE (IntelliJ IDEA, VS Code or Eclipse)

**Instalation:**
1. Clone the repository:
```bash
git clone https://github.com/zckLab/API-NINJA.git
```
2. Navigate to the project foldder:
```bash
cd API-NINJA
```
3. Build the project:
```bash
mvn clean install
```
4. Run the application:
```bash
mvn spring-boot:run
```
5. Access the H2 Console: `http://localhost:8081/h2-console`

## Security Roadmap
Planned security enhancements include:
- Implementation of Spring Security (JWT Authentication)
- API Rate Limiting
- Comprehensive logging and activity monitoring
### Wait for the next releases!
