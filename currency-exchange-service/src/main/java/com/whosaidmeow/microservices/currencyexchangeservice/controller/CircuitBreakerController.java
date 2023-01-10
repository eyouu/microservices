package com.whosaidmeow.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

//    @Retry(name = "sample-api", fallbackMethod = "defaultFallback")
//    @Bulkhead(name = "sample-api")
//    @RateLimiter(name = "sample-api")
    @GetMapping("/sample-api")
    @CircuitBreaker(name = "default", fallbackMethod = "defaultFallback")
    public String sampleApi() {
        log.info("---------> Calling sample api");

        ResponseEntity<String> response = new RestTemplate()
                .getForEntity("http://localhost:4444/fake", String.class);

        return response.getBody();
    }

    private String defaultFallback(Exception ex) {
        log.info("====FALLBACK CAL====");
        return "Default fallback message";
    }
}
