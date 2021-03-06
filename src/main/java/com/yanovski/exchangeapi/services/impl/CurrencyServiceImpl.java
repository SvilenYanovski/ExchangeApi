package com.yanovski.exchangeapi.services.impl;

import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;
import com.yanovski.exchangeapi.entities.ApiCurrency;
import com.yanovski.exchangeapi.entities.ApiCurrencyRate;
import com.yanovski.exchangeapi.repositories.ApiConversionRepository;
import com.yanovski.exchangeapi.repositories.ApiCurrencyRepository;
import com.yanovski.exchangeapi.repositories.ApiRatesRepository;
import com.yanovski.exchangeapi.services.CurrencyProxyService;
import com.yanovski.exchangeapi.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final ApiCurrencyRepository currencyRepository;
    private final CurrencyProxyService proxyService;
    private final ApiRatesRepository ratesRepository;

    private final List<CurrencyDto> currenciesCache = new ArrayList<>();

    @PostConstruct
    public void initCurrencies() {
        List<ApiCurrency> currencies = currencyRepository.findAll();
        if (currencies.isEmpty()) {
            List<CurrencyDto> dtos = proxyService.getCurrencies();
            this.createCurrencies(dtos);
            this.currenciesCache.addAll(dtos);
        } else {
            this.currenciesCache.addAll(currencies.stream().map(CurrencyDto::new).collect(Collectors.toList()));
        }

        Pageable limit = PageRequest.of(0,100);
        List<ApiCurrencyRate> rates = ratesRepository.findAll(limit).toList();
        if (rates.isEmpty()) {
            saveRates(proxyService.getRates());
        }
    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return this.currenciesCache;
    }

    @Override
    public void saveRates(List<RateDto> rates) {
        LocalDateTime now = LocalDateTime.now();

        ratesRepository.saveAll(rates.stream()
                .map(dto -> ApiCurrencyRate.builder()
                        .currency(currencyRepository.findByCode(dto.getCode()))
                        .rate(dto.getRate())
                        .createdAt(now)
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public Double getLatestRate(String currencyCode) {
        ApiCurrency currency = currencyRepository.findByCode(currencyCode);
        ApiCurrencyRate rate = ratesRepository.findFirstByCurrencyOrderByCreatedAtDesc(currency);
        return rate == null ? 0 : rate.getRate();
    }

    @Override
    public List<RateDto> getAllCurrencyRates(String code) {
        ApiCurrency currency = currencyRepository.findByCode(code);
        return ratesRepository.findAllByCurrency(currency).stream().map(RateDto::new).collect(Collectors.toList());
    }

    private void createCurrencies(List<CurrencyDto> currencies) {
        LocalDateTime now = LocalDateTime.now();
        for (CurrencyDto dto: currencies) {
            currencyRepository.save(ApiCurrency.builder()
                    .code(dto.getCode())
                    .name(dto.getName().replace("??", "")) // thank you fixer.io, thank you so much...
                    .createdAt(now)
                    .build());
        }
    }
}
