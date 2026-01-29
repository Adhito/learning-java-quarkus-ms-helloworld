package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus!";
    }

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloName(@PathParam("name") String name) {
        return greetingService.greet(name);
    }

    @GET
    @Path("/greeting")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting greeting() {
        return new Greeting("Hello", "Welcome to Quarkus Microservices!");
    }

    @GET
    @Path("/greeting/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting greetingWithName(@PathParam("name") String name) {
        return new Greeting("Hello " + name, "Welcome to Quarkus Microservices!");
    }
}
