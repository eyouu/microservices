package com.whosaidmeow.microservices.currencyconversionservice.feign;

import com.whosaidmeow.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeFeignProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion exchange(@PathVariable String from, @PathVariable String to);
}
