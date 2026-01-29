# Hello World Quarkus Microservice

A simple REST microservice built with Quarkus framework.

## Requirements

- Java 21+
- Maven 3.8+

## Project Structure

```
src/
├── main/
│   ├── java/com/example/
│   │   ├── Greeting.java           # DTO for JSON responses
│   │   ├── GreetingResource.java   # REST endpoint definitions
│   │   └── GreetingService.java    # Business logic service
│   └── resources/
│       └── application.properties  # Application configuration
└── test/
    └── java/com/example/
        └── GreetingResourceTest.java
```

## Running the Application

### Development Mode (with hot reload)

```bash
mvn quarkus:dev
```

### Production Build

```bash
mvn package
java -jar target/quarkus-app/quarkus-run.jar
```

### Docker

Two Dockerfiles are provided:

| File | Description | Image Size | Startup Time |
|------|-------------|------------|--------------|
| `Dockerfile` | Native executable (GraalVM) | ~50MB | ~10-50ms |
| `Dockerfile.jvm` | JVM-based | ~200MB | ~1-2s |

#### Native Image (Recommended)

Uses GraalVM to compile to a native executable for fast startup and low memory footprint:

```bash
# Build native image
docker build -t hello-world-v1 .

# Run the container
docker run -p 8080:8080 hello-world-v1
```

> **Note:** Native build takes longer to compile (several minutes) but results in faster startup and lower memory usage at runtime.

#### JVM Image

Traditional JVM-based image for development or when native compilation is not needed:

```bash
# Build JVM image
docker build -f Dockerfile.jvm -t hello-world-v1:jvm .

# Run the container
docker run -p 8080:8080 hello-world-v1:jvm

# Run with custom JVM options
docker run -p 8080:8080 -e JAVA_OPTS="-Xms256m -Xmx512m" hello-world-v1:jvm
```

#### Common Docker Commands

```bash
# Run in detached mode
docker run -d -p 8080:8080 --name hello-world hello-world-v1

# View logs
docker logs hello-world

# Stop container
docker stop hello-world
```

## API Endpoints

| Method | Endpoint | Description | Response Type |
|--------|----------|-------------|---------------|
| GET | `/api/hello` | Simple hello message | text/plain |
| GET | `/api/hello/{name}` | Personalized greeting | text/plain |
| GET | `/api/greeting` | JSON greeting object | application/json |
| GET | `/api/greeting/{name}` | JSON greeting with name | application/json |
| GET | `/health` | Health check status | application/json |

## Example Requests

```bash
# Simple hello
curl http://localhost:8080/api/hello
# Response: Hello from Quarkus!

# Hello with name
curl http://localhost:8080/api/hello/John
# Response: Hello, John! Welcome to the Quarkus microservice.

# JSON greeting
curl http://localhost:8080/api/greeting
# Response: {"message":"Hello","description":"Welcome to Quarkus Microservices!"}

# JSON greeting with name
curl http://localhost:8080/api/greeting/John
# Response: {"message":"Hello John","description":"Welcome to Quarkus Microservices!"}

# Health check
curl http://localhost:8080/health
# Response: {"status":"UP","checks":[]}
```

## Running Tests

```bash
mvn test
```

## Configuration

Configuration is managed in `src/main/resources/application.properties`:

| Property | Default | Description |
|----------|---------|-------------|
| `quarkus.http.port` | 8080 | HTTP server port |
| `quarkus.application.name` | hello-world-v1 | Application name |

## Tech Stack

- **Quarkus 3.17.5** - Supersonic Subatomic Java framework
- **RESTEasy Reactive** - REST endpoints
- **Jackson** - JSON serialization
- **SmallRye Health** - Health check endpoints
- **ArC** - CDI dependency injection
