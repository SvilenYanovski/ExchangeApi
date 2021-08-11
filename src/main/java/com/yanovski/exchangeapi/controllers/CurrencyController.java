package com.yanovski.exchangeapi.controllers;

import com.yanovski.exchangeapi.client.api_model.Currency;
import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;
import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.services.CurrencyProxyService;
import com.yanovski.exchangeapi.services.CurrencyService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("currency")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("list")
    public List<CurrencyDto> getProvidedCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("rate/{code}")
    @ApiOperation(value = "Returns the latest Rate for a currency Code", response = Double.class)
    public Double getLatestRate(@PathVariable String code) {
        return currencyService.getLatestRate(code);
    }

    @GetMapping("rates/list/{code}")
    @ApiOperation(value = "Returns all Rates for a currency Code", response = Double.class)
    public List<RateDto> getAllCurrencyRates(@PathVariable String code) {
        return currencyService.getAllCurrencyRates(code);
    }
}
