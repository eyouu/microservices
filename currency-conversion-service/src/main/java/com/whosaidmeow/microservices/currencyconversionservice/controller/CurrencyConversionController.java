package com.whosaidmeow.microservices.currencyconversionservice.controller;

import com.whosaidmeow.microservices.currencyconversionservice.feign.CurrencyExchangeFeignProxy;
import com.whosaidmeow.microservices.currencyconversionservice.model.CurrencyConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeFeignProxy currencyExchangeFeignProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convert(@PathVariable String from,
                                      @PathVariable String to,
                                      @PathVariable BigDecimal quantity) {
        CurrencyConversion entity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        from, to).getBody();

        return buildCurrencyConversion(entity, quantity);
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertUsingFeign(@PathVariable String from,
                                      @PathVariable String to,
                                      @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyExchangeFeignProxy.exchange(from, to);

        return buildCurrencyConversion(currencyConversion, quantity);
    }

    private CurrencyConversion buildCurrencyConversion(CurrencyConversion entity, BigDecimal quantity) {
        return CurrencyConversion.builder()
                .id(entity.getId())
                .from(entity.getFrom())
                .to(entity.getTo())
                .conversionMultiple(entity.getConversionMultiple())
                .quantity(quantity)
                .totalCalculationAmount(entity.getConversionMultiple().multiply(quantity))
                .environment(entity.getEnvironment())
                .build();
    }
}
