package com.yanovski.exchangeapi.controllers;

import com.yanovski.exchangeapi.client.api_model.Currency;
import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;
import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.services.CurrencyProxyService;
import com.yanovski.exchangeapi.services.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("currency")
@AllArgsConstructor
@Api(value="currency", description="Currency API endpoints")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("list")
    @ApiOperation(value = "Get all provided symbols/currencies.")
    public List<CurrencyDto> getProvidedCurrencies() {
        log.info("action=CurrencyController.getProvidedCurrencies");
        return currencyService.getAllCurrencies();
    }

    @GetMapping("rate/{code}")
    @ApiOperation(value = "Returns the latest Rate for a currency Code", response = Double.class)
    public Double getLatestRate(@PathVariable String code) {
        log.info("action=CurrencyController.getLatestRate(code={})", code);
        return currencyService.getLatestRate(code);
    }

    @GetMapping("rates/list/{code}")
    @ApiOperation(value = "Returns all Rates for a currency Code", response = Double.class)
    public List<RateDto> getAllCurrencyRates(@PathVariable String code) {
        log.info("action=CurrencyController.getAllCurrencyRates(code={})", code);
        return currencyService.getAllCurrencyRates(code);
    }
}
