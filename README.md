# Vehicle Compliance System

A Spring Boot–based backend system for retrieving and managing vehicle compliance information, including Registration Certificate (RC), Insurance, and Pollution Under Control (PUC) details.

## Features

* Vehicle lookup using vehicle registration number
* Retrieval of vehicle, RC, insurance, and PUC details
* Automatic compliance status calculation based on document expiry dates
* RESTful API with JSON responses
* Layered architecture using Controller, Service, Repository, and DTO components
* MySQL database integration using Spring Data JPA and Hibernate
* Global exception handling with appropriate HTTP error responses

## Technology Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** Postgre SQL
* **ORM:** Spring Data JPA / Hibernate
* **API:** REST
* **Build Tool:** Maven

## API Endpoint

### Get Vehicle Compliance Details

```text
GET /api/vehicles/{vehicleNumber}
```

Returns vehicle information along with RC, insurance, and PUC details and their current compliance status.

### Example Response

```json
{
  "vehicleNumber": "KA01AB1234",
  "ownerName": "Example Owner",
  "vehicleType": "CAR",
  "manufacturer": "Example Manufacturer",
  "model": "Example Model",
  "rc": {
    "status": "VALID"
  },
  "insurance": {
    "status": "EXPIRING_SOON"
  },
  "puc": {
    "status": "VALID"
  }
}
```

## Project Architecture

```text
Client
   ↓
REST Controller
   ↓
Service Layer
   ↓
Repository Layer
   ↓
PostgreSQL Database
```

The service layer applies business logic to determine whether compliance documents are **VALID**, **EXPIRING_SOON**, or **EXPIRED** before returning the response.

## Error Handling

If a vehicle number does not exist, the API returns an HTTP `404 NOT FOUND` response with a JSON error message.

## Future Enhancements

* Frontend web application
* User authentication and authorization
* Vehicle record CRUD operations
* Advanced search and filtering
* Compliance notifications
* Deployment to cloud infrastructure
