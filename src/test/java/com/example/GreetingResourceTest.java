package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
            .when().get("/api/hello")
            .then()
                .statusCode(200)
                .body(is("Hello from Quarkus!"));
    }

    @Test
    void testHelloNameEndpoint() {
        given()
            .when().get("/api/hello/World")
            .then()
                .statusCode(200)
                .body(containsString("World"));
    }

    @Test
    void testGreetingEndpoint() {
        given()
            .when().get("/api/greeting")
            .then()
                .statusCode(200)
                .body("message", is("Hello"))
                .body("description", is("Welcome to Quarkus Microservices!"));
    }

    @Test
    void testGreetingWithNameEndpoint() {
        given()
            .when().get("/api/greeting/John")
            .then()
                .statusCode(200)
                .body("message", is("Hello John"));
    }
}
