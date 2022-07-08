package com.example.flightyapigateway.config;

import com.example.flightyapigateway.filter.RouteGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(
            RouteLocatorBuilder builder,
            RouteGatewayFilterFactory routeFactory) {
        return builder.routes()
                .route("flighty-auth-ms", r -> r.path("/auth-ms/**")
                        .filters(f ->
                                f.rewritePath("/(?<path>.*)", "/$\\{path}")
                                        .filter(routeFactory.apply(new RouteGatewayFilterFactory.Config())))
                        .uri("lb://FLIGHTY-AUTH-MS"))
                .build();
    }
}
