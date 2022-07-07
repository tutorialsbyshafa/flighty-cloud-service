package security;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.http.entity.ContentType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class SecurityFilter implements GatewayFilterFactory<SecurityFilter.Config> {

    private final Config config;

    public SecurityFilter(Config config) {
        this.config = config;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (this::filter);
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        ServerHttpRequest request = exchange.getRequest();


        ServerWebExchange mutatedExchange = internalAuthentication(exchange, request);

        return chain.filter(mutatedExchange);
    }

    private ServerWebExchange internalAuthentication(ServerWebExchange exchange, ServerHttpRequest request) {
        System.err.println("Internal");
        ServerHttpRequest modifiedRequest = request.mutate()
                .method(HttpMethod.POST)
                .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
//                .header("Authorization", config.getInternal().getAuthorization())
                .header("X-Gateway-Token", "token")
                .build();

        return exchange.mutate()
                .request(modifiedRequest)
                .build();
    }

    @Data
    @ConfigurationProperties("service.security")
    public static class Config {

        private External external;
        private Internal internal;

        @Data
        public static class External {
            private String username;
            private String password;
        }

        @Data
        public static class Internal {
            private String source;
            private String authorization;
        }

    }
}
