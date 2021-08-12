package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.repositories.ApiConversionRepository;
import com.yanovski.exchangeapi.repositories.ApiCurrencyRepository;
import com.yanovski.exchangeapi.repositories.ApiRatesRepository;
import com.yanovski.exchangeapi.services.impl.ConversionServiceImpl;
import com.yanovski.exchangeapi.services.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {
    private CurrencyService currencyService;

    private final ApiCurrencyRepository currencyRepository = Mockito.mock(ApiCurrencyRepository .class);
    private final CurrencyProxyService proxyService = Mockito.mock(CurrencyProxyService .class);
    private final ApiRatesRepository ratesRepository = Mockito.mock(ApiRatesRepository .class);

    private final List<CurrencyDto> currencies = new ArrayList<>();

    @BeforeEach
    void init() {
        currencyService = new CurrencyServiceImpl(currencyRepository, proxyService, ratesRepository);
        currencies.add(CurrencyDto.builder().code("EUR").name("Eur").build());
        currencies.add(CurrencyDto.builder().code("USD").name("Usd").build());
    }

    @Test
    void testServiceCreated() {
        assertNotNull(currencyService);
    }
}
