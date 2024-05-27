# Pokemon Cache

## Overview
Pokemon Cache is a simple Spring Boot project aimed at caching and retrieving Pokemon data using a RESTful API. It leverages APIs to fetch data and caches it locally for faster retrieval.

## Features
- **Caching:** Store Pokemon data locally to reduce API calls and improve performance.
- **Search:** Easily search for Pokemon by name or ID.
- **RESTful API:** Expose endpoints to interact with the cached Pokemon data.

## Installation
To use this project, follow these steps:

1. **Clone the Repository:**
   ```
   git clone https://github.com/MeherchandPN/pokemon-cache.git
   ```

2. **Navigate to the Spring Boot Project:**
   ```
   cd pokemon-cache/pokemon-cache-springboot
   ```

3. **Build and Run the Project:**
   ```
   ./mvnw spring-boot:run
   ```

4. **Access the APIs:**
   - The APIs will be available at `http://localhost:8080`.

## API Endpoints

### Retrieve Pokemon by ID
- **URL:** `/api/pokemon/{id}`
- **Method:** `GET`
- **Parameters:**
  - `id`: The ID of the Pokemon to retrieve.
- **Response:** JSON object containing the details of the Pokemon.

### Retrieve Pokemon by Name
- **URL:** `/api/pokemon/name/{name}`
- **Method:** `GET`
- **Parameters:**
  - `name`: The name of the Pokemon to retrieve.
- **Response:** JSON object containing the details of the Pokemon.

### Delete Pokemon by ID
- **URL:** `/api/pokemon/{id}`
- **Method:** `DELETE`
- **Parameters:**
  - `id`: The ID of the Pokemon to delete.
- **Response:** JSON object containing a success message.

### Add Pokemon
- **URL:** `/api/pokemon`
- **Method:** `POST`
- **Body:** JSON object containing the details of the new Pokemon.
- **Response:** JSON object containing the details of the added Pokemon.

## Contributing
Contributions are welcome! If you'd like to contribute to this project, feel free to fork the repository and submit a pull request with your changes.
