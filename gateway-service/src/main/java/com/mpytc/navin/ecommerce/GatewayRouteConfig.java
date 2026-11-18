package com.mpytc.navin.ecommerce;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Discovery routes (/<name>-service/**, order 0) win while the service is registered.
                // When it is down, its route disappears and we land here instead of the Vue route.
                .route("service-fallback", r -> r.order(Ordered.LOWEST_PRECEDENCE - 1)
                        .path("/*-service/**")
                        .uri("forward:/fallback/service-unavailable"))
                .route("vuejs", r -> r.order(Ordered.LOWEST_PRECEDENCE)
                        .path("/**")
                        .and()
                        .not(p -> p.path("/scalar/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**"))
                        .filters(f -> f.saveSession().tokenRelay())
                        .uri("http://[::1]:5173"))
                .build();
    }


}
