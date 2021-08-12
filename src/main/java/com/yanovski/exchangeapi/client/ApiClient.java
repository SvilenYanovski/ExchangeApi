package com.yanovski.exchangeapi.client;

import com.yanovski.exchangeapi.client.api_model.CurrencyResponse;
import com.yanovski.exchangeapi.client.api_model.RatesResponse;

/**
 * Api Client for 3rd party service to retrieve exchange data.
 */
public interface ApiClient {
    /**
     * Get available currencies for tracking rates.
     *
     * @return response containing list with supported symbols/currencies.
     */
    CurrencyResponse getCurrencies();

    /**
     * Get rates for all supported symbols/currencies.
     * Rates are given relative to base currency EUR!
     *
     * @return response containing latest rates
     */
    RatesResponse getRates();
}
