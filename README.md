
# Niche Event Hub 🎟️

> A full-stack event management application built with Java, Spring Boot, and Thymeleaf.

**Project Status:** *Completed Prototype* This project was built as an intensive 3-week learning exercise to master Core Java, OOP principles, and the Spring Boot ecosystem. It demonstrates a complete "vertical slice" of an event booking platform, including database relationships, business logic, and a server-side rendered frontend.

---

## 🚀 Features Implemented

### 1. User & Event Management
* **Data Models:** robust `User` and `Event` entities mapped to PostgreSQL using JPA/Hibernate.
* **Database Seeding:** Automatically seeds the database with test users and events on startup for easy demonstration.

### 2. Booking System (Business Logic)
* **Complex Relationships:** Implemented a Many-to-Many relationship between Users and Events using an intermediate `Booking` entity to store registration metadata.
* **Validation Rules:**
    * Prevents users from registering for the same event twice.
    * Prevents registration if an event has reached capacity.
    * **Atomic Transactions:** Uses `@Transactional` to ensure data consistency between booking creation and capacity updates.

### 3. Frontend & UX
* **Event Catalog:** Responsive grid layout displaying all upcoming events with real-time "spots left" calculation.
* **Simulated Authentication:** A session-based "dummy login" system that allows testing the app as different users (Alice, Bob, Charlie) without the overhead of full Spring Security.
* **User Dashboard:** A dedicated "My Events" page showing a personalized list of registered events for the logged-in user.
* **Flash Messaging:** User feedback (success/error messages) using Spring `RedirectAttributes`.

---

## 🛠️ Technology Stack

* **Language:** Java 21 (LTS)
* **Framework:** Spring Boot 3.3 (Web, Data JPA, DevTools)
* **Database:** PostgreSQL
* **Frontend:** Thymeleaf (Server-side templating), CSS3 (Grid/Flexbox)
* **Build Tool:** Maven

---

## ⚙️ How to Run Locally

### Prerequisites
* Java 21+ installed.
* PostgreSQL installed and running.

### 1. Database Setup
Create a local database named `event_hub_db`.
```sql
CREATE DATABASE event_hub_db;
````

### 2\. Configure Application

Open `src/main/resources/application.properties` and update your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/event_hub_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

### 3\. Run the App

Run the application using your IDE or the command line:

```bash
./mvnw spring-boot:run
```

*The app will automatically populate the database with test data on the first run.*

### 4\. Access the App

Open your browser to: `http://localhost:8080/login`

* **Test User IDs:** Enter `1`, `2`, or `3` to log in as different users.

-----

## 🧠 Key Learnings & Concepts Applied

* **Inversion of Control (IoC):** Used Spring's Dependency Injection (`@Autowired`) to manage the service layer and repositories.
* **ORM Mastery:** Mapped complex SQL relationships (One-to-Many, Many-to-Many) to Java Objects using Hibernate annotations.
* **MVC Architecture:** Clean separation of concerns with Controllers handling HTTP requests, Services handling business rules, and Repositories handling data access.
* **JPQL:** Wrote custom `@Query` methods to efficiently fetch related data (e.g., finding all events for a specific user).

-----

