//package security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class RouteConfiguration {
//
//    //@Autowired
//    final CustomFilter securityFilter;
//
//    @Bean
//    public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("ms-emanat", p -> p
//                        .path("/api/v1/emanat/**")
//                        .filters(f -> f
//                                .filter(securityFilter)
//                                .secureHeaders()
//                        )
//                        .uri("lb://MS-EMANAT"))
//                .build();
//    }
//
//}