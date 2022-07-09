package com.example.flightyapigateway.config;

import static com.example.flightyapigateway.util.Constant.AUTH_MS_ID;
import static com.example.flightyapigateway.util.Constant.AUTH_MS_PATH;
import static com.example.flightyapigateway.util.Constant.AUTH_MS_URI;

import com.example.flightyapigateway.filter.RouteGatewayFilterFactory;
import com.example.flightyapigateway.model.ResponseModel;
import java.time.LocalDateTime;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, RouteGatewayFilterFactory routeFactory) {
        return builder.routes()
                .route(AUTH_MS_ID, spec -> buildRoute(routeFactory, spec, AUTH_MS_PATH, AUTH_MS_ID, AUTH_MS_URI))
                .build();
    }

    private Buildable<Route> buildRoute(RouteGatewayFilterFactory routeFactory, PredicateSpec predicateSpec,
                                        String path, String id, String uri) {
        return predicateSpec.path(path)
                .filters(f -> addGatewayFilters(routeFactory, f, id))
                .uri(uri);
    }

    private GatewayFilterSpec addGatewayFilters(RouteGatewayFilterFactory routeFactory,
                                                GatewayFilterSpec filter,
                                                String serviceId) {
        return filter
                .rewritePath("/(?<path>.*)", "/$\\{path}")
                .filter(routeFactory.apply(new RouteGatewayFilterFactory.Config()))
                .modifyResponseBody(
                        Object.class,
                        ResponseModel.class,
                        MediaType.APPLICATION_JSON_VALUE,
                        (ex, rs) -> generateResponseBody(ex, rs, serviceId));
    }

    private Mono<ResponseModel> generateResponseBody(ServerWebExchange exchange,
                                                     Object responseBody,
                                                     String serviceId) {
        return Mono.just(
                ResponseModel.of(
                        exchange.getResponse().getStatusCode(),
                        LocalDateTime.now(),
                        serviceId,
                        responseBody));
    }
}
