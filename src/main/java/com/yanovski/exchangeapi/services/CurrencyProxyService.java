package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;

import java.util.List;

/**
 * Service to retrieve data from the HTTP Client
 */
public interface CurrencyProxyService {
    /**
     * Gets all provided currencies/symbols
     *
     * @return list
     */
    List<CurrencyDto> getCurrencies();

    /**
     * Get latest rates for the provided currencies
     *
     * @return list
     */
    List<RateDto> getRates();
}
