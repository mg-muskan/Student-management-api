# Spring Boot Student Management REST API

A production-style RESTful CRUD application built with **Spring Boot 3**, **PostgreSQL**, and **JPA/Hibernate**. Demonstrates layered architecture, DTO pattern, conditional service injection, and REST best practices.

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Framework | Spring Boot 3.5 |
| Language | Java 17 |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Mapping | ModelMapper |
| Build | Maven |
| Boilerplate | Lombok |

---

## Features

- Full **CRUD** operations via RESTful endpoints
- **DTO pattern** — entities never exposed directly to API consumers
- **PUT / PATCH** support — full and partial updates
- **Conditional bean injection** for pluggable payment providers (Razorpay / Stripe)
- Layered architecture: Controller → Service → Repository → DB
- Proper HTTP status codes (200, 201, 204)

---

## Project Structure

```
src/main/java/com/starter/springboot/
├── controller/
│   ├── StudentAPIController.java          # Active REST endpoints
│   └── StudentAPIDeprecatedController.java # Legacy endpoints (deprecated)
├── service/
│   ├── StudentService.java                # Service interface
│   └── impl/StudentServiceImpl.java       # Business logic
├── repository/
│   └── StudentRepository.java             # JPA repository
├── entity/
│   └── Student.java                       # JPA entity
├── dto/
│   ├── StudentDTO.java                    # Response DTO
│   └── AddStudentRequestDTO.java          # Request DTO
├── config/
│   └── MapperConfig.java                  # ModelMapper config
└── payment/
    ├── PaymentService.java                # Payment interface
    ├── RazorPayService.java               # Razorpay implementation
    └── StripePayment.java                 # Stripe implementation
```

---

## API Endpoints

Base URL: `http://localhost:8080`

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `GET` | `/students` | Get all students | 200 OK |
| `GET` | `/students/{id}` | Get student by ID | 200 OK |
| `POST` | `/students` | Create new student | 201 Created |
| `PUT` | `/students/{id}` | Full update | 200 OK |
| `PATCH` | `/students/{id}` | Partial update | 200 OK |
| `DELETE` | `/students/{id}` | Delete student | 204 No Content |

### Sample Requests

**Create a student**
```http
POST /students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Partial update (PATCH)**
```http
PATCH /students/1
Content-Type: application/json

{
  "email": "new.email@example.com"
}
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL 14+

### Database Setup

```sql
CREATE DATABASE starter;
```

### Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/starter
spring.datasource.username=your_username
spring.datasource.password=your_password
```

> **Tip:** Use environment variables or a `.env` file — never commit credentials.

### Run

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`. Hibernate auto-creates the `student` table on first run.

---

## Payment Provider (Conditional Beans)

The app demonstrates Spring's `@ConditionalOnProperty` pattern for swappable implementations.

Switch providers by changing `application.properties`:

```properties
# Use Razorpay (default)
payment.provider=razorpay

# Or switch to Stripe
payment.provider=stripe
```

No code change required — Spring wires the correct bean at startup.

---

## Architecture

```
HTTP Request
    │
    ▼
Controller        ← validates input, maps to DTOs
    │
    ▼
Service Layer     ← business logic, uses ModelMapper
    │
    ▼
Repository        ← Spring Data JPA (auto-implements queries)
    │
    ▼
PostgreSQL
```

---

## Running Tests

```bash
mvn test
```
