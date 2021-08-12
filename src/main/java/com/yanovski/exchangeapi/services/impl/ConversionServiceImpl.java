package com.yanovski.exchangeapi.services.impl;

import com.yanovski.exchangeapi.config.api_errors.exceptions.ApiEntityNotFoundException;
import com.yanovski.exchangeapi.dto.ConversionDto;
import com.yanovski.exchangeapi.entities.ApiCurrency;
import com.yanovski.exchangeapi.entities.ApiCurrencyConversion;
import com.yanovski.exchangeapi.entities.ApiCurrencyRate;
import com.yanovski.exchangeapi.repositories.ApiConversionRepository;
import com.yanovski.exchangeapi.repositories.ApiCurrencyRepository;
import com.yanovski.exchangeapi.repositories.ApiRatesRepository;
import com.yanovski.exchangeapi.services.ConversionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConversionServiceImpl implements ConversionService {
    private final ApiCurrencyRepository currencyRepository;
    private final ApiRatesRepository ratesRepository;
    private final ApiConversionRepository conversionRepository;

    @Override
    public ConversionDto createConversion(String fromCode, String toCode, String amount) {
        LocalDateTime now = LocalDateTime.now();
        ApiCurrency fromCurrency = currencyRepository.findByCode(fromCode);
        ApiCurrencyRate fromRate = ratesRepository.findFirstByCurrencyOrderByCreatedAtDesc(fromCurrency);
        ApiCurrency toCurrency = currencyRepository.findByCode(toCode);
        ApiCurrencyRate toRate = ratesRepository.findFirstByCurrencyOrderByCreatedAtDesc(toCurrency);
        Double amountToConvert = Double.parseDouble(amount);

        Double xRate = toRate.getRate() / fromRate.getRate();
        Double result = amountToConvert * xRate;

        ApiCurrencyConversion conversion = ApiCurrencyConversion.builder()
                .createdAt(now)
                .queryFrom(fromCurrency.getCode())
                .queryTo(toCurrency.getCode())
                .queryAmount(amount)
                .queryFromRateId(fromRate.getId())
                .queryToRateId(toRate.getId())
                .queryFromRateValue(fromRate.getRate().toString())
                .queryToRateValue(toRate.getRate().toString())
                .calculatedCrossRate(xRate.toString())
                .calculatedResult(result.toString())
                .build();
        ApiCurrencyConversion created = conversionRepository.save(conversion);

        return new ConversionDto(created);
    }

    @Override
    public List<ConversionDto> getAllConversions() {
        return conversionRepository.findAll().stream().map(ConversionDto::new).collect(Collectors.toList());
    }

    @Override
    public Double getConversionRate(String fromCode, String toCode) {
        ApiCurrency fromCurrency = currencyRepository.findByCode(fromCode);
        ApiCurrencyRate fromRate = ratesRepository.findFirstByCurrencyOrderByCreatedAtDesc(fromCurrency);
        ApiCurrency toCurrency = currencyRepository.findByCode(toCode);
        ApiCurrencyRate toRate = ratesRepository.findFirstByCurrencyOrderByCreatedAtDesc(toCurrency);

        return toRate.getRate() / fromRate.getRate();
    }

    @Override
    public ConversionDto getById(String id) {
        ApiCurrencyConversion result = conversionRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ApiEntityNotFoundException(ApiCurrencyConversion.class, "id", id));
        return new ConversionDto(result);
    }

    @Override
    public List<ConversionDto> getConversionBetweenDates(String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDateTime start = LocalDateTime.of(LocalDate.parse(fromDate, formatter), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.parse(toDate, formatter), LocalTime.of(23, 59, 59));
        return conversionRepository.getAllBetweenDates(start, end).stream().map(ConversionDto::new).collect(Collectors.toList());
    }

}
