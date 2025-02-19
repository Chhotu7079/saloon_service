# Salon Service API

## Overview
The **Salon Service API** allows users to book salon appointments, manage services, and handle customer interactions seamlessly. This API is designed for salon owners and customers to facilitate online appointment scheduling and service management.

## Base URL
```
https://api.salonservice.com/v1
```

## Authentication
All endpoints require authentication via a **Bearer Token**.

### Headers Example
```http
Authorization: Bearer YOUR_ACCESS_TOKEN
Content-Type: application/json
```

---

## Endpoints

### 1. User Authentication

#### Register a New User
```http
POST /auth/register
```
##### Request Body
```json
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "securepassword",
  "phone": "1234567890"
}
```
##### Response
```json
{
  "message": "User registered successfully",
  "token": "your-jwt-token"
}
```

#### Login User
```http
POST /auth/login
```
##### Request Body
```json
{
  "email": "johndoe@example.com",
  "password": "securepassword"
}
```
##### Response
```json
{
  "token": "your-jwt-token"
}
```

---

### 2. Salon Services

#### Get All Services
```http
GET /services
```
##### Response
```json
[
  {
    "id": 1,
    "name": "Hair Cut",
    "description": "Professional hair cutting services",
    "price": 15.99
  },
  {
    "id": 2,
    "name": "Facial",
    "description": "Skin treatment and facial massage",
    "price": 30.50
  }
]
```

#### Get Service by ID
```http
GET /services/{service_id}
```
##### Response
```json
{
  "id": 1,
  "name": "Hair Cut",
  "description": "Professional hair cutting services",
  "price": 15.99
}
```

---

### 3. Appointments

#### Book an Appointment
```http
POST /appointments
```
##### Request Body
```json
{
  "user_id": 1,
  "service_id": 2,
  "appointment_date": "2025-02-20T10:00:00Z"
}
```
##### Response
```json
{
  "message": "Appointment booked successfully",
  "appointment_id": 123
}
```

#### Get User Appointments
```http
GET /appointments/user/{user_id}
```
##### Response
```json
[
  {
    "appointment_id": 123,
    "service": "Facial",
    "date": "2025-02-20T10:00:00Z",
    "status": "Confirmed"
  }
]
```

---

### 4. Reviews

#### Add a Review
```http
POST /reviews
```
##### Request Body
```json
{
  "user_id": 1,
  "service_id": 2,
  "rating": 5,
  "comment": "Great experience!"
}
```
##### Response
```json
{
  "message": "Review submitted successfully"
}
```

#### Get Reviews for a Service
```http
GET /reviews/{service_id}
```
##### Response
```json
[
  {
    "user": "John Doe",
    "rating": 5,
    "comment": "Great experience!",
    "date": "2025-02-20"
  }
]
```

---

## Error Handling
All error responses will be returned in the following format:
```json
{
  "error": "Error message here"
}
```
