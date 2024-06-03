# UUID Generator and Storage

This project implements a simple UUID generator based on the UUID version 1 specification and provides functionality to store and retrieve generated UUIDs using an in-memory H2 database with Spring Data JPA.

## Table of Contents

- [Description](#description)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Running the Application](#running-the-application)
  - [API Endpoints](#api-endpoints)
- [Question 2: Auditing System Design](#question-2-auditing-system-design)
  - [Design Overview](#design-overview)
  - [UUID Generation and Storage](#uuid-generation-and-storage)
  - [Data Persistence](#data-persistence)
  - [Pagination](#pagination)
  - [API Endpoint](#api-endpoint)

## Description

This project generates UUIDs using the UUID version 1 specification, which includes a timestamp, node identifier, and a clock sequence. It also allows storing and retrieving these UUIDs for auditing purposes, with a focus on high-frequency writes and infrequent reads.

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 In-memory Database
- Lombok
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/folaulau/uuid.git
    cd uuid
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run tests using Maven:
    ```sh
    mvn clean test
    ```

### Question 2: Auditing System Design

#### Design Overview

For auditing purposes, we need to be able to list all the UUIDs generated on a given day. Writes are very frequent and are in the critical path, while reads are infrequent. Here’s how we designed the system to meet these requirements:

#### UUID Generation and Storage

- UUIDs are generated using the UUID version 1 specification.
- Each generated UUID is stored in an H2 in-memory database along with a timestamp of when it was created.
- The `UID` entity class represents the UUID and includes fields for the UUID string and the creation timestamp.

#### Data Persistence

- We use Spring Data JPA for ORM and repository management.
- The `UIDRepository` interface extends `JpaRepository` to provide basic CRUD operations.
- We added a custom query method `findByDate` to fetch UUIDs created on a specific date. This method uses a native query to cast the `createdAt` timestamp to a date and supports pagination to handle potentially large datasets.

#### Pagination

- To handle potentially large datasets, the `findByDate` method supports pagination. This allows us to efficiently retrieve subsets of the data without loading everything into memory.
- The `UIDDAOImp` class implements the `UIDDAO` interface and uses the repository to perform database operations, including the paginated query for fetching UUIDs by date.

#### API Endpoint

- A RESTful endpoint is provided to access the UUIDs generated on a given day with pagination.
- The `UIDController` class handles incoming HTTP requests and uses the `UIDDAO` service to interact with the database.

