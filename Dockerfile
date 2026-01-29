# Stage 1: Build native executable
FROM ghcr.io/graalvm/native-image-community:21 AS build
WORKDIR /app

# Install Maven
RUN microdnf install -y maven && microdnf clean all

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src

# Build native executable
RUN mvn package -Pnative -DskipTests -B

# Stage 2: Run with minimal image
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /app

# Copy native executable
COPY --from=build /app/target/*-runner /app/application

# Create non-root user
USER 1001

EXPOSE 8080

ENTRYPOINT ["/app/application", "-Dquarkus.http.host=0.0.0.0"]
