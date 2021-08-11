package com.yanovski.exchangeapi.services.impl;

import com.yanovski.exchangeapi.client.ApiClient;
import com.yanovski.exchangeapi.client.api_model.Currency;
import com.yanovski.exchangeapi.client.api_model.CurrencyResponse;
import com.yanovski.exchangeapi.client.api_model.RatesResponse;
import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;
import com.yanovski.exchangeapi.services.CurrencyProxyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyProxyServiceImpl implements CurrencyProxyService {
    private final ApiClient apiClient;

    @Override
    public List<CurrencyDto> getCurrencies() {
        List<CurrencyDto> currencies = new ArrayList<>();
        CurrencyResponse response = apiClient.getCurrencies();
        Map<String, String> symbols = response == null ? new HashMap<>() : response.getSymbols();

        symbols.forEach((key, value) -> currencies.add(CurrencyDto.builder()
                .code(key)
                .name(value)
                .build()));

        return currencies;
    }

    @Override
    public List<RateDto> getRates() {
        List<RateDto> rates = new ArrayList<>();
        RatesResponse response = apiClient.getRates();
        Map<String, Double> ratesMap = response == null ? new HashMap<>() : response.getRates();

        ratesMap.forEach((key, value) -> rates.add(RateDto.builder()
                .code(key)
                .rate(value)
                .build()));

        return rates;
    }
}
