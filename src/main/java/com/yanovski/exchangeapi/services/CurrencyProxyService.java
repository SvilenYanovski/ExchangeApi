package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;

import java.util.List;

public interface CurrencyProxyService {
    List<CurrencyDto> getCurrencies();

    List<RateDto> getRates();
}
