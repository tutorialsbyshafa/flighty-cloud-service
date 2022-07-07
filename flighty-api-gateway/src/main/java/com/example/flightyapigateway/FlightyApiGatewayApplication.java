package com.example.flightyapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import security.SecurityFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(value = {
        SecurityFilter.Config.class
})
public class FlightyApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightyApiGatewayApplication.class, args);
    }


}
