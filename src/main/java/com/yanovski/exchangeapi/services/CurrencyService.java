package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.ConversionDto;
import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();

    void saveRates(List<RateDto> rates);

    Double getLatestRate(String currencyCode);

    List<RateDto> getAllCurrencyRates(String code);

    ConversionDto createConversion(String fromCode, String toCode, String amount);

    List<ConversionDto> getAllConversions();
}
