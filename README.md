# Student Management (Spring Boot + MySQL)

CRUD REST API for student details: **name, roll number, marks**.

## Prerequisites
- Java 25+
- Maven 3.8+
- MySQL 8+ running on localhost:3306

## Setup
1. Edit `src/main/resources/application.properties` and set your MySQL username/password.
   The `studentdb` database and `students` table are created automatically.
2. Run:
   ```
   mvn spring-boot:run
   ```
3. API base URL: `http://localhost:8080/api/students`

## Endpoints
| Method | URL                   | Description        |
|--------|-----------------------|--------------------|
| POST   | /api/students         | Add a student      |
| GET    | /api/students         | View all students  |
| GET    | /api/students/{id}    | View one student   |
| PUT    | /api/students/{id}    | Update a student   |
| DELETE | /api/students/{id}    | Delete a student   |

## Try it
```
curl -X POST localhost:8080/api/students -H "Content-Type: application/json" \
  -d '{"name":"Anu Menon","rollNumber":"CS101","marks":88.5}'

curl localhost:8080/api/students
curl localhost:8080/api/students/1

curl -X PUT localhost:8080/api/students/1 -H "Content-Type: application/json" \
  -d '{"name":"Anu Menon","rollNumber":"CS101","marks":92}'

curl -X DELETE localhost:8080/api/students/1
```
