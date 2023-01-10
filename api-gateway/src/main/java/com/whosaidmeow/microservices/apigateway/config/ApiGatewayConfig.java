package com.whosaidmeow.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MyTestHeaderKey", "MyTestHeaderVal")
                                .addRequestParameter("ParamKey", "ParamVal"))
                        .uri("http://httpbin.org:80")) // random site, just to check how routing configuration works. We will be redirected here after /get
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange")) // lb = load balancer; Any link that starts with '/currency-exchange' will be redirected to currency-exchange service.
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-new/**") // we can redirect from set uped link to any that we want
                        .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", // (?<segment>.*) -> will copy everything that goes after /
                                "/currency-conversion-feign/${segment}")) // and will past it here
                        .uri("lb://currency-conversion"))
                .build();
    }
}
