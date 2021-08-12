package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.repositories.ApiConversionRepository;
import com.yanovski.exchangeapi.repositories.ApiCurrencyRepository;
import com.yanovski.exchangeapi.repositories.ApiKeysRepository;
import com.yanovski.exchangeapi.repositories.ApiRatesRepository;
import com.yanovski.exchangeapi.services.impl.ApiKeysServiceImpl;
import com.yanovski.exchangeapi.services.impl.ConversionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConversionServiceTest {
    private ConversionService conversionService;

    private final ApiCurrencyRepository currencyRepository = Mockito.mock(ApiCurrencyRepository .class);
    private final ApiRatesRepository ratesRepository = Mockito.mock(ApiRatesRepository .class);
    private final ApiConversionRepository conversionRepository = Mockito.mock(ApiConversionRepository .class);

    @BeforeEach
    void init() {
        conversionService = new ConversionServiceImpl(currencyRepository, ratesRepository, conversionRepository);
    }

    @Test
    void testServiceCreated() {
        assertNotNull(conversionService);
    }
}
