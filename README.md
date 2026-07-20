# 📝 TodoList – Full Stack Task Management Application

A full-stack Task Management Web Application developed using **Spring Boot** for the backend and **Angular** for the frontend.

The application allows authenticated users to securely manage their tasks through a modern web interface. It provides user authentication, JWT-based security, task CRUD operations, database integration, and communication between the Angular frontend and Spring Boot REST API.

---

## 🚀 Features

### 🔐 Authentication & Security

- User registration
- User login
- JWT-based authentication
- Secure password encryption using BCrypt
- Spring Security integration
- Protected routes with Angular Guards
- JWT token management
- HTTP Interceptor for authenticated API requests
- User-specific task management

### 📋 Task Management

- Create a new task
- View tasks
- View task details
- Update existing tasks
- Delete tasks
- Manage task status
- Set task start date
- Set task end date
- Associate tasks with authenticated users

### 👤 User Management

- User registration
- User authentication
- Secure user accounts
- Role management
- User-specific data access

### 🔌 REST API

- RESTful API architecture
- CRUD operations
- JSON data exchange
- Backend and frontend integration
- API documentation with Swagger / OpenAPI

---

# 🛠️ Technologies & Tools

## Backend

- **Java 21**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **Hibernate**
- **JWT (JSON Web Token)**
- **MySQL**
- **Maven**
- **Lombok**
- **Swagger / OpenAPI**

## Frontend

- **Angular**
- **TypeScript**
- **HTML5**
- **CSS3**
- **PrimeNG**
- **RxJS**

## Database

- **MySQL**

## Development Tools

- **IntelliJ IDEA**
- **Visual Studio Code**
- **Postman**
- **Swagger UI**
- **Git**
- **GitHub**

---

# 🏗️ Architecture

The application follows a **Full Stack architecture** composed of three main layers:

```text
┌─────────────────────────────┐
│       Angular Frontend      │
│                             │
│  Components                 │
│  Services                   │
│  Guards                     │
│  HTTP Interceptors          │
│  PrimeNG UI                 │
└──────────────┬──────────────┘
               │
               │ HTTP / REST API
               │ JSON + JWT
               ▼
┌─────────────────────────────┐
│      Spring Boot Backend    │
│                             │
│  REST Controllers           │
│  Services                   │
│  Repositories               │
│  DTOs                       │
│  Spring Security            │
│  JWT Authentication         │
└──────────────┬──────────────┘
               │
               │ JPA / Hibernate
               ▼
┌─────────────────────────────┐
│          MySQL              │
│                             │
│  Users                      │
│  Tasks                      │
└─────────────────────────────┘
```

---

# 📂 Project Structure

The project is divided into two main parts:

```text
TodoList/
│
├── backend/
│
└── frontend/
```

---

## 🔙 Backend Structure

The Spring Boot backend follows a layered architecture:

```text
backend/
└── src/
    └── main/
        ├── java/
        │   └── ...
        │       ├── controller/
        │       ├── service/
        │       ├── repository/
        │       ├── entity/
        │       ├── dto/
        │       ├── security/
        │       └── configuration/
        │
        └── resources/
            └── application.properties
```

### Backend Layers

- **Controller** – Handles HTTP requests and exposes REST API endpoints.
- **Service** – Contains the application's business logic.
- **Repository** – Handles database operations using Spring Data JPA.
- **Entity** – Represents database tables using JPA entities.
- **DTO** – Used to transfer data between the backend and frontend.
- **Security** – Handles authentication, authorization, JWT generation, and JWT validation.
- **Configuration** – Contains application and security configuration.

---

## 🎨 Frontend Structure

The Angular application follows a **feature-based architecture**:

```text
frontend/
└── src/
    └── app/
        ├── core/
        │   ├── guards/
        │   ├── interceptors/
        │   ├── models/
        │   └── services/
        │
        └── features/
            ├── auth/
            ├── landing/
            └── todos/
                └── CRUD/
```

### Core

The `core` folder contains the application's essential and reusable elements.

#### `guards/`

Contains Angular route guards used to protect private routes and prevent unauthorized access.

#### `interceptors/`

Contains HTTP interceptors responsible for processing HTTP requests, including adding the JWT token to authenticated API requests.

#### `models/`

Contains TypeScript interfaces and models used to represent application data.

#### `services/`

Contains Angular services responsible for communicating with the Spring Boot REST API and managing application data.

---

### Features

The `features` folder contains the main functional modules of the application.

#### `auth/`

Handles user authentication features:

- Login
- Registration
- Authentication management
- JWT token handling

#### `landing/`

Contains the application's landing page and initial user interface.

#### `todos/CRUD/`

Contains the task management functionality:

- **Create** – Create a new task
- **Read** – Display and retrieve tasks
- **Update** – Modify an existing task
- **Delete** – Remove a task

---

# 🔐 Authentication Flow

The application uses **JWT (JSON Web Token)** authentication.

The authentication process works as follows:

```text
User
 │
 │ Login
 ▼
Angular Frontend
 │
 │ POST Login Request
 ▼
Spring Boot API
 │
 │ Authenticate User
 ▼
Spring Security
 │
 │ Validate Credentials
 ▼
JWT Token Generated
 │
 │
 ▼
Angular Frontend
 │
 │ Store JWT Token
 ▼
HTTP Interceptor
 │
 │ Add Authorization Header
 │
 │ Authorization: Bearer <JWT>
 ▼
Protected REST API
```

### Authentication Process

1. The user registers an account.
2. The user logs in using their credentials.
3. Spring Security authenticates the user.
4. The backend generates a JWT token.
5. The frontend receives the JWT token.
6. The token is stored on the client side.
7. The Angular HTTP Interceptor adds the token to protected requests.
8. The backend validates the JWT token.
9. Authenticated users can access protected resources.

---

# 🗄️ Database

The application uses **MySQL** as its relational database.

The backend communicates with MySQL through:

- Spring Data JPA
- Hibernate
- JPA Repositories

The main entities include:

### User

The user entity contains information such as:

- ID
- Name
- Surname
- Username
- Email
- Password
- Role

### Task

The task entity contains information such as:

- Task ID
- Title
- Description
- Status
- Start Date
- End Date
- User association

---

# 🔌 REST API

The backend exposes RESTful endpoints used by the Angular frontend.

## Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/register` | Register a new user |
| POST | `/api/v1/login` | Authenticate a user |

## Tasks

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Retrieve tasks |
| GET | `/api/tasks/{id}` | Retrieve a task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |

> The exact endpoints may vary depending on the backend controller configuration.

---

# 📖 API Documentation

The REST API can be documented and tested using **Swagger / OpenAPI**.

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

It allows developers to:

- View available API endpoints
- View request and response models
- Test REST API operations
- Test authentication-protected endpoints

---

# ⚙️ Installation & Setup

## 📌 Prerequisites

Before running the application, make sure you have installed:

- Java 21
- Maven
- Node.js
- npm
- Angular CLI
- MySQL
- Git

---

# 1️⃣ Clone the Repository

Clone the project from GitHub:

```bash
git clone https://github.com/yourusername/todolist.git
```

Navigate to the project:

```bash
cd todolist
```

---

# 2️⃣ Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE todolist;
```

Configure the database connection in:

```text
backend/src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todolist
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace:

```text
your_password
```

with your MySQL password.

---

# 3️⃣ Run the Backend

Navigate to the backend folder:

```bash
cd backend
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The backend will be available at:

```text
http://localhost:8080
```

---

# 4️⃣ Run the Frontend

Open another terminal and navigate to the Angular project:

```bash
cd frontend
```

Install the required dependencies:

```bash
npm install
```

Start the Angular development server:

```bash
ng serve
```

The frontend will be available at:

```text
http://localhost:4200
```

---

# 🔄 Application Workflow

The general application workflow is:

```text
1. User opens the application
          │
          ▼
2. Landing Page
          │
          ▼
3. User registers or logs in
          │
          ▼
4. Backend authenticates the user
          │
          ▼
5. JWT Token is returned
          │
          ▼
6. User accesses the Todo List
          │
          ▼
7. User creates / reads / updates / deletes tasks
          │
          ▼
8. Angular sends REST API requests
          │
          ▼
9. Spring Boot processes requests
          │
          ▼
10. MySQL stores and retrieves data
```

---

# 🧪 Testing

The REST API can be tested using:

- Postman
- Swagger UI

The frontend can be tested directly through the Angular development server.

Backend tests can be executed using Maven:

```bash
mvn test
```

---

# 📸 Screenshots

## Landing Page

_Add screenshot here._

## Login

_Add screenshot here._

## Registration

_Add screenshot here._

## Task Dashboard

_Add screenshot here._

## Create Task

_Add screenshot here._

## Update Task

_Add screenshot here._

---

# 🔮 Future Improvements

Possible future improvements include:

- Task categories
- Task priorities
- Search functionality
- Task filtering
- Pagination
- Sorting
- Email verification
- Password reset
- User profile management
- Dark mode
- Notifications
- Task reminders
- File attachments
- Docker containerization
- CI/CD pipeline
- Cloud deployment

---

# 🎯 Learning Objectives

This project provided practical experience in:

- Java development
- Spring Boot application development
- REST API design
- Spring Security
- JWT authentication
- Password encryption
- Spring Data JPA
- Hibernate ORM
- MySQL database integration
- Angular development
- TypeScript
- HTTP communication
- Angular Guards
- HTTP Interceptors
- PrimeNG
- Frontend and backend integration
- API testing
- Git and GitHub

---

# 👨‍💻 Author

## Mohamed Amine Rekik

Full Stack Developer interested in:

- Java
- Spring Boot
- Angular
- TypeScript
- REST APIs
- Spring Security
- MySQL

---

# 📄 License

This project was developed for educational and internship purposes.
