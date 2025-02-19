


# Salon Service API

## Overview
The **Salon Service API** allows users to book salon appointments, manage services, and handle customer interactions seamlessly. This API is designed for salon owners and customers to facilitate online appointment scheduling and service management.

## Table of Contents

- [User Service](#user-service)
  - [Authentication Endpoints](#authentication-endpoints)
  - [User Endpoints](#user-endpoints)
- [Salon Service](#salon-service)
- [Category Microservice](#category-microservice)
- [Service Offering Microservice](#service-offering-microservice)
- [Booking Microservice](#booking-microservice)
- [Payment Microservice](#payment-microservice)

## User Service

### Authentication Endpoints

1.  **POST /auth/signup**
    -   Description: Registers a new user.
    -   Request Body: `SignupDto` (user registration details)
    -   Response:
        -   Success: "User created successfully."
        -   Body: Authentication response object.

2.  **POST /auth/login**
    -   Description: Logs in a user.
    -   Request Body: `LoginDto` (email and password)
    -   Response:
        -   Success: "User logged in successfully."
        -   Body: Authentication response object.

3.  **GET /auth/access-token/refresh-token/{refreshToken}**
    -   Description: Generates a new access token using a refresh token.
    -   Parameters: `refreshToken` (PathVariable)
    -   Response:
        -   Success: "Refresh token received successfully."
        -   Body: Authentication response object.

### User Endpoints

1.  **GET /api/users/profile**
    -   Description: Retrieves the user profile.
    -   Request Header: `Authorization` (JWT token)
    -   Response: `UserDTO` (user's profile information)
    -   HTTP Status: 200 OK

2.  **GET /api/users/{userId}**
    -   Description: Retrieves user details by ID.
    -   Path Parameter: `userId`
    -   Response: `UserDTO` (user's details)
    -   HTTP Status: 200 OK
    -   Error Handling: `UserException` if user not found.

## Salon Service

-   Base URL: `/api/salons`

1.  **POST /api/salons** - Create Salon
    -   Description: Creates a new salon.
    -   Authorization: JWT token.
    -   Request Body: `SalonDTO`
    -   Response: 201 Created, `SalonDTO`

2.  **PUT /api/salons/{salonId}** - Update Salon
    -   Description: Updates an existing salon.
    -   Path Parameter: `salonId`
    -   Request Body: `Salon`
    -   Response: 200 OK, `SalonDTO`

3.  **GET /api/salons** - Get All Salons
    -   Description: Retrieves a list of all salons.
    -   Response: 200 OK, `List<SalonDTO>`

4.  **GET /api/salons/{salonId}** - Get Salon By ID
    -   Description: Retrieves the details of a salon by its ID.
    -   Path Parameter: `salonId`
    -   Response: 200 OK, `SalonDTO`
    -   Error Handling: Exception if the salon does not exist.

5.  **GET /api/salons/search** - Search Salon By City
    -   Description: Searches for salons in a specific city.
    -   Query Parameter: `city`
    -   Response: 200 OK, `List<SalonDTO>`

6.  **GET /api/salons/owner** - Get Salon By Owner
    -   Description: Retrieves the salon owned by the authenticated user.
    -   Authorization: JWT token.
    -   Response: 200 OK, `Salon`

## Category Microservice

-   Base URL: `/api/categories`
-   Owner-Specific Base URL: `/api/categories/salon-owner`

1.  **GET /api/categories** - Get All Categories
    -   Description: Fetches all available categories.
    -   Response: 200 OK, `List<Category>`

2.  **GET /api/categories/salon/{id}** - Get Categories by Salon ID
    -   Description: Retrieves categories associated with a specific salon.
    -   Path Parameter: `id` (ID of the salon)
    -   Header Parameter: `"Authorization"` (JWT token of the user)
    -   Response: 200 OK, `Set<Category>`
    -   Feign Clients:
        -   `UserFeignClient`: Validates the user's JWT token.
        -   `SalonFeignClient`: Fetches salon details by ID.

3.  **GET /api/categories/{id}** - Get Category by ID
    -   Description: Retrieves a single category by its ID.
    -   Path Parameter: `id` (ID of the category)
    -   Response: 200 OK (If the category is found) or 404 Not Found (If the category does not exist)
    -   Feign Clients:
        -   `UserFeignClient`: Validates the user's JWT token.
        -   `SalonFeignClient`: Fetches salon details by ID.

4.  **DELETE /api/categories/{id}** - Delete Category by ID
    -   Description: Deletes a category by its ID.
    -   Path Parameter: `id` (ID of the category)
    -   Response: 204 No Content (If deletion is successful) or 404 Not Found (If the category does not exist)

5.  **POST /api/categories/salon-owner** - Create Category (Salon Owner)
    -   Description: Allows a salon owner to create a new category for their salon.
    -   Header Parameter: `"Authorization"` (JWT token of the salon owner)
    -   Request Body: `Category` (Details of the new category)
    -   Response: 201 Created, `Category` (Details of the newly created category)
    -   Feign Clients:
        -   `SalonFeignClient`: Validates salon ownership via JWT token.

## Service Offering Microservice

-   Base URL: `/api/service-offering`
-   Owner-Specific Base URL: `/api/service-offering/salon-owner`

1.  **GET /api/service-offering/salon/{salonId}** - Get Services by Salon ID
    -   Description: Retrieves all services offered by a specific salon, with an optional filter by category.
    -   Path Parameter: `salonId` (ID of the salon)
    -   Query Parameter (Optional): `categoryId` (ID of the category to filter services)
    -   Response: 200 OK, `Set<ServiceOffering>` (List of services offered by the salon)

2.  **GET /api/service-offering/{serviceId}** - Get Service by ID
    -   Description: Retrieves details of a specific service by its ID.
    -   Path Parameter: `serviceId` (ID of the service)
    -   Response: 200 OK, `ServiceOffering` (Details of the service)
    -   Error Handling: Throws an exception if the service does not exist.

3.  **GET /api/service-offering/list/{ids}** - Get Services by Multiple IDs
    -   Description: Retrieves details of multiple services by their IDs.
    -   Path Parameter: `ids` (A set of service IDs (comma-separated))
    -   Response: 200 OK, `Set<ServiceOffering>` (List of services matching the provided IDs)

4.  **POST /api/service-offering/salon-owner** - Create Service (Salon Owner)
    -   Description: Allows a salon owner to create a new service under their salon.
    -   Authorization: Header: `"Authorization"` — JWT token.
    -   Request Body: `ServiceDTO` (Details of the service to be created)
    -   Response: 201 Created, `ServiceOffering` (Details of the newly created service)
    -   Feign Clients:
        -   `SalonFeignClient`: Retrieves salon details by the owner's JWT.
        -   `CategoryFeignClient`: Fetches the category details.

5.  **PUT /api/service-offering/salon-owner/{serviceId}** - Update Service (Salon Owner)
    -   Description: Allows a salon owner to update an existing service.
    -   Path Parameter: `serviceId` (ID of the service to update)
    -   Request Body: `ServiceOffering` (Updated service details)
    -   Response: 200 OK (On successful update) or 404 Not Found (If the service does not exist)

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
