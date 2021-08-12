package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.ConversionDto;
import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.dto.RateDto;

import java.util.List;

/**
 * Service to operate with the Currencies and Rates
 */
public interface CurrencyService {
    /**
     * Lists all provided currencies
     *
     * @return list
     */
    List<CurrencyDto> getAllCurrencies();

    /**
     * Saves a list with latest rates
     *
     * @param rates list with latest rates
     */
    void saveRates(List<RateDto> rates);

    /**
     * Retrieve latest provided rate for a currency
     *
     * @param currencyCode currency code
     * @return numeric rate
     */
    Double getLatestRate(String currencyCode);

    /**
     * Get all existing rates for a currency (historical data)
     *
     * @param code currency code
     * @return list with rates
     */
    List<RateDto> getAllCurrencyRates(String code);
}
