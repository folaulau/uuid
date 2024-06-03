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
- [License](#license)

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

## Usage

### Running the Application

Run the application using Maven:
```sh
mvn spring-boot:run
