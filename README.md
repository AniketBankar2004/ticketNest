# 🎬 TicketNest

**TicketNest** is a full-stack movie ticket booking platform built with **Spring Boot and React**. Users can browse movies, view available shows, select seats, book tickets, manage their bookings, and cancel confirmed reservations.

The project focuses on building a real-world booking workflow with **JWT authentication, PostgreSQL persistence, REST APIs, and concurrency-safe seat reservation**.

---

## ✨ Features

### 🎥 Movie & Show Management

* Browse available movies
* View movie details
* View available shows and timings
* Display movie posters
* Select a specific show before booking

### 🎟️ Seat Booking

* Interactive seat selection
* Seats organized using rows and seat numbers
* Seat availability checking
* Prevents multiple users from booking the same seat
* Automatic ticket generation for selected seats
* Calculates total booking amount

### 👤 Authentication & Authorization

* User registration and login
* JWT-based authentication
* Protected booking APIs
* Authenticated users can access their own bookings
* Backend validates booking ownership before cancellation

### 📋 Booking Management

* View personal bookings
* View movie, show time, seats, amount, and booking status
* Cancel confirmed bookings
* Cancelled bookings remain visible with their updated status

### 🔒 Concurrency-Safe Booking

TicketNest uses database-level locking to handle concurrent booking requests.

When multiple users attempt to reserve the same seat at the same time, the backend uses **pessimistic write locking** to ensure that only one transaction can successfully reserve the seat.

This helps prevent the classic **double-booking problem** in ticket reservation systems.

---

## 🛠️ Tech Stack

### Backend

| Technology          | Purpose                        |
| ------------------- | ------------------------------ |
| ☕ Java              | Programming language           |
| 🌱 Spring Boot      | Backend framework              |
| 🔐 Spring Security  | Authentication & authorization |
| 🎫 JWT              | Stateless authentication       |
| 🐘 PostgreSQL       | Relational database            |
| 🗃️ Spring Data JPA | Database interaction           |
| 🛠️ Maven           | Dependency management & build  |

### Frontend

| Technology         | Purpose                |
| ------------------ | ---------------------- |
| ⚛️ React           | UI development         |
| ⚡ Vite             | Frontend tooling       |
| 🎨 Tailwind CSS    | Styling                |
| 🔄 Axios           | REST API communication |
| 🔔 React Hot Toast | User notifications     |

---

## 🏗️ Architecture

```text
                    ┌─────────────────────┐
                    │      React UI       │
                    │                     │
                    │  Vite + Tailwind    │
                    └──────────┬──────────┘
                               │
                               │ REST API
                               ▼
                    ┌─────────────────────┐
                    │   Spring Boot API   │
                    │                     │
                    │  Controllers        │
                    │  Services           │
                    │  Repositories       │
                    │  Spring Security    │
                    └──────────┬──────────┘
                               │
                         JPA / Hibernate
                               │
                               ▼
                    ┌─────────────────────┐
                    │     PostgreSQL      │
                    │                     │
                    │ Users               │
                    │ Movies              │
                    │ Shows               │
                    │ Bookings            │
                    │ Tickets             │
                    └─────────────────────┘
```

---

## 📂 Project Structure

```text
TicketNest/
│
├── ticketNestBackend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/aniket/ticketNest/
│   │       │       ├── controller/
│   │       │       ├── service/
│   │       │       ├── repository/
│   │       │       ├── model/
│   │       │       ├── dto/
│   │       │       ├── security/
│   │       │       └── exceptions/
│   │       │
│   │       └── resources/
│   │           └── application.properties
│   │
│   └── pom.xml
│
└── frontend/
    └── ticketNestFrontend/
        ├── src/
        │   ├── components/
        │   ├── pages/
        │   ├── services/
        │   └── App.jsx
        │
        ├── package.json
        └── vite.config.js
```

---

## 🔄 Booking Flow

```text
User
 │
 ▼
Select Movie
 │
 ▼
Select Show
 │
 ▼
Fetch Available Seats
 │
 ▼
Select Seats
 │
 ▼
Send Booking Request
 │
 ▼
Spring Boot API
 │
 ▼
Acquire Database Lock
 │
 ▼
Check Seat Availability
 │
 ├── Already Booked ──► Reject Request
 │
 └── Available
          │
          ▼
      Create Booking
          │
          ▼
      Create Tickets
          │
          ▼
      Commit Transaction
          │
          ▼
      Return Booking
```

---

## 🔐 Authentication Flow

TicketNest uses JWT for stateless authentication.

```text
Login
  │
  ▼
Spring Security
  │
  ▼
Validate Credentials
  │
  ▼
Generate JWT
  │
  ▼
Frontend stores token
  │
  ▼
Axios sends:
Authorization: Bearer <token>
  │
  ▼
JWT Filter
  │
  ▼
Authenticate Request
  │
  ▼
Controller
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have installed:

* Java 21+
* Node.js 18+
* PostgreSQL
* Maven

---

### 1. Clone the Repository

```bash
git clone https://github.com/AniketBankar2004/ticketNest.git

cd ticketNest
```

---

### 2. Backend Setup

Navigate to the backend:

```bash
cd ticketNestBackend
```

Configure your PostgreSQL database and application properties.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketnest
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
```

Configure your JWT secret according to your application's security configuration.

Start the backend:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend runs on:

```text
http://localhost:8080
```

---

### 3. Frontend Setup

Navigate to the frontend:

```bash
cd frontend/ticketNestFrontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend will be available at the URL provided by Vite, typically:

```text
http://localhost:5173
```

---

## 📡 API Overview

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

### Movies

```http
GET /api/v1/movies
GET /api/v1/movies/{id}
```

### Shows

```http
GET /api/shows/movie/{movieId}
GET /api/v1/shows/{showId}/tickets
```

### Bookings

```http
POST   /api/shows/{showId}/book
GET    /api/bookings/my
DELETE /api/bookings/{bookingId}
```

Protected endpoints require:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 🧩 Database Model

The core entities are:

```text
User
 │
 └───────────────┐
                 │
                 ▼
              Booking
                 │
                 ├──────────► Show
                 │             │
                 │             └──► Movie
                 │
                 └──────────► Ticket
                                │
                                └── Seat Number
```

### Booking

Stores information about:

* User
* Show
* Total amount
* Booking time
* Booking status

### Ticket

Stores:

* Seat number
* Ticket price
* Show
* Booking
* Ticket status

A unique constraint on the show and seat combination helps enforce seat uniqueness at the database level.

---

## 🛡️ Handling Double Booking

One of the main backend challenges in a movie booking system is concurrent seat selection.

For example:

```text
User A ───────► Seat A5
                  │
                  ├── Booking request
                  │
User B ───────► Seat A5
                  │
                  └── Booking request
```

Without proper concurrency control, both requests could potentially see the seat as available.

TicketNest handles this using a **transactional booking operation with pessimistic database locking**, ensuring that concurrent requests cannot reserve the same seat simultaneously.

---

## 🎯 Future Improvements

* [ ] Redis-based rate limiting
* [ ] Redis caching for movie/show data
* [ ] Kafka-based asynchronous notifications
* [ ] Email confirmation after booking
* [ ] Online payment integration
* [ ] WebSocket-based real-time seat updates
* [ ] Dockerized deployment
* [ ] CI/CD with GitHub Actions
* [ ] Cloud deployment
* [ ] Admin dashboard for movies and shows

---

## 📸 Screenshots

Add screenshots of the application here:

```text
screenshots/
├── home.png
├── movie-details.png
├── seat-selection.png
├── booking-confirmation.png
└── my-bookings.png
```

Then include them in the README:

```markdown
![Home Page](screenshots/home.png)

![Movie Details](screenshots/movie-details.png)

![Seat Selection](screenshots/seat-selection.png)

![My Bookings](screenshots/my-bookings.png)
```

---

## 📚 What I Learned

Building TicketNest involved working with:

* REST API design
* Spring Boot application architecture
* Spring Security and JWT authentication
* JPA entity relationships
* PostgreSQL database design
* Transaction management
* Pessimistic database locking
* Concurrent booking handling
* React state management
* Axios API integration
* Tailwind CSS
* Full-stack authentication flow

---

## 👨‍💻 Author

**Aniket Bankar**

GitHub: [@AniketBankar2004](https://github.com/AniketBankar2004)

---

## ⭐ Support

If you found this project useful or interesting, consider giving the repository a ⭐ on GitHub.

**Built with Java, Spring Boot, React, and PostgreSQL.**
