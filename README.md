# Smart University Management System

## Overview

The **Smart University Management System (SUMS)** is a web-based application for managing core university information in one place. It provides an administrative dashboard to manage students, lecturers, departments, courses, and student course enrollments.

Built with **Spring Boot**, **Thymeleaf**, and **MySQL**, the project follows a layered architecture that separates the user interface, business logic, and database operations.

## Key Features

- **Secure administration** – Login-protected system with Spring Security and BCrypt password hashing.
- **Dashboard** – Displays summary counts for students, lecturers, departments, and courses.
- **Student management** – Add, view, edit, and delete student records.
- **Lecturer management** – Add, view, edit, and delete lecturer records.
- **Department management** – Maintain department information.
- **Course management** – Create, update, view, and delete course records.
- **Enrollment management** – Enroll students in courses and manage enrollment records.
- **Responsive web interface** – Server-rendered pages created with Thymeleaf, HTML, and CSS.

## Technologies

- **Language:** Java 21
- **Framework:** Spring Boot 3.5.5
- **Web:** Spring MVC, Thymeleaf, HTML, CSS
- **Security:** Spring Security with BCrypt password encoding
- **Database:** MySQL
- **Persistence:** Spring Data JPA / Hibernate
- **Build tool:** Maven

## Project Structure

```text
Smart-University-System/
└── backend/
    └── sums/
        ├── src/
        │   ├── main/
        │   │   ├── java/com/university/sums/
        │   │   │   ├── config/          # Security configuration
        │   │   │   ├── controller/      # Web controllers
        │   │   │   ├── entity/          # JPA entity classes
        │   │   │   ├── repository/      # Database repositories
        │   │   │   ├── service/         # Business logic
        │   │   │   └── SumsApplication.java
        │   │   └── resources/
        │   │       ├── static/          # CSS and images
        │   │       ├── templates/       # Thymeleaf pages
        │   │       └── application.properties
        │   ├── test/                    # Application tests
        │   ├── pom.xml                  # Maven configuration
        │   ├── mvnw                     # Maven wrapper
        │   └── mvnw.cmd
        └── README.md
```

## Architecture

The application uses a layered MVC-style architecture:

```text
Thymeleaf Pages
      ↓
Controllers
      ↓
Services
      ↓
Repositories
      ↓
MySQL Database
```

- **Entities** represent the system data: `Student`, `Lecturer`, `Department`, `Course`, and `Enrollment`.
- **Controllers** receive web requests and return the relevant Thymeleaf pages.
- **Services** implement the business logic.
- **Repositories** use Spring Data JPA to access MySQL data.

## Prerequisites

Before running the project, install:

- Java 21 or later
- MySQL Server
- Maven (optional, as the Maven Wrapper is included)

## Database Setup

1. Start your MySQL server.
2. Create the database:

```sql
CREATE DATABASE sums;
```

3. Update the database settings in `backend/sums/src/main/resources/application.properties` if your MySQL username, password, or port are different:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sums
spring.datasource.username=root
spring.datasource.password=your_password
```

The application uses `spring.jpa.hibernate.ddl-auto=update`, so the database tables are created or updated automatically when the application starts.

## Run the Application

Open PowerShell in the project folder and run:

```powershell
cd backend\sums
.\mvnw.cmd spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080) in your browser.

## Default Login

| Field | Value |
| --- | --- |
| Username | `admin` |
| Password | `admin123` |

> Change the default credentials before deploying or sharing the system beyond development use.

## Main Pages

- `/` – Dashboard
- `/students` – Student management
- `/lecturers` – Lecturer management
- `/departments` – Department management
- `/courses` – Course management
- `/enrollments` – Enrollment management
- `/login` – Login page

## Future Enhancements

- Add multiple user roles such as administrator, lecturer, and student.
- Allow students to access their own profile and course enrollments.
- Add search, filtering, pagination, and reporting.
- Add validation messages and a stronger password policy.
- Deploy the system to a cloud platform.

## License

This project is intended for educational purposes.
