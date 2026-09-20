# Vehicle Compliance System

A full-stack vehicle compliance system built with **Java Spring Boot, React, and PostgreSQL**. The system allows users to retrieve vehicle compliance information using either a vehicle registration number or a vehicle image.

> **V1 Status:** This version uses a local/demo PostgreSQL database. It does not retrieve live vehicle data from VAHAN/Parivahan.

## Features

- Vehicle lookup using vehicle registration number
- Vehicle lookup using an uploaded vehicle image
- Number plate recognition using Tesseract OCR
- Image preprocessing for improved OCR results
- Fuzzy vehicle-number matching using Levenshtein Edit Distance
- Retrieval of vehicle, RC, insurance, and PUC details
- Automatic compliance status calculation
- Status classification:
  - `VALID`
  - `EXPIRING_SOON`
  - `EXPIRED`
- RESTful backend APIs with JSON responses
- React-based frontend interface
- Loading and error handling
- Layered Spring Boot architecture
- PostgreSQL database integration using Spring Data JPA and Hibernate
- Global exception handling with appropriate HTTP responses

## Technology Stack

### Backend

- **Language:** Java
- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA / Hibernate
- **API:** REST
- **Build Tool:** Maven
- **OCR:** Tesseract OCR / Tess4J

### Frontend

- **Library:** React
- **Build Tool:** Vite
- **Language:** JavaScript
- **Styling:** CSS
- **Icons:** Lucide React

## System Architecture

```text
                    ┌─────────────────────┐
                    │    React Frontend   │
                    └──────────┬──────────┘
                               │
                         HTTP Requests
                               │
                               ▼
                    ┌─────────────────────┐
                    │   REST Controller   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Service Layer     │
                    │  Business Logic     │
                    └──────────┬──────────┘
                               │
                    ┌──────────┴──────────┐
                    │                     │
                    ▼                     ▼
             ┌─────────────┐      ┌─────────────┐
             │ Repository  │      │ OCR Service │
             └──────┬──────┘      └─────────────┘
                    │
                    ▼
             ┌─────────────┐
             │ PostgreSQL  │
             │   Database  │
             └─────────────┘
