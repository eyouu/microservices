package com.whosaidmeow.microservices.currencyexchangeservice.controller;

import com.whosaidmeow.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.whosaidmeow.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to)
                        .orElseThrow(() -> new RuntimeException(format("Currency from %s to %s not found.", from, to)));

        log.info("retrieveExchangeValue was called from: {} to: {}", from, to);

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        return currencyExchange;
    }
}
