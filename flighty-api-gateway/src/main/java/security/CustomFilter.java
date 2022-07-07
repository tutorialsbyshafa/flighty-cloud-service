package security;//package com.payriff.bff.emanat.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Objects;

@Component
@Slf4j
public class CustomFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Make your business logic, this is a simple sample.
//        log.info("Headers : " + request.getHeaders());

        if (!request.getHeaders().containsKey("Authorization")) {
//            log.error("Authorization header not found!");
            return this.onError(exchange, "Authorization header not found", HttpStatus.UNAUTHORIZED);
        }

        String authorizationHeader = Objects.requireNonNull(request.getHeaders().get("Authorization"))
                .stream()
                .findFirst().orElseThrow(() -> {
                    throw new SecurityException("Authorization header not found!");
                });

        if (!this.isAuthorizationValid(authorizationHeader)) {
//            log.error("Invalid Authorization header!");
            return this.onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }

        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().
                header("secret", RandomStringUtils.random(10)).
                build();


        return chain.filter(exchange);
    }

    private boolean isAuthorizationValid(String authHeader) {

        boolean authorized = false;

        if (authHeader != null) {

            String[] authHeaderSplit = authHeader.split("\\s");

            for (int i = 0; i < authHeaderSplit.length; i++) {
                String token = authHeaderSplit[i];
                if (token.equalsIgnoreCase("Basic")) {

                    String credentials = new String(Base64.getDecoder().decode(authHeaderSplit[i + 1]));
                    int index = credentials.indexOf(":");
                    if (index != -1) {
                        String username = credentials.substring(0, index).trim();
                        String password = credentials.substring(index + 1).trim();
                        authorized = username.equals("emanatprod") && password.equals("em@n4tp7d");
                    }
                }
            }
        }
//        log.info("Authorization: " + authorized);

        return authorized;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }


}