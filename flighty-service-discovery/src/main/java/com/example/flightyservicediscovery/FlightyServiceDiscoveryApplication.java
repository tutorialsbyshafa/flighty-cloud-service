package com.example.flightyservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FlightyServiceDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightyServiceDiscoveryApplication.class, args);
    }

}
