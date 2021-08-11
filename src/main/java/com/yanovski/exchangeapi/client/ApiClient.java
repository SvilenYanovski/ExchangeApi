package com.yanovski.exchangeapi.client;

import com.yanovski.exchangeapi.client.api_model.Currency;
import com.yanovski.exchangeapi.client.api_model.CurrencyResponse;
import com.yanovski.exchangeapi.client.api_model.RatesResponse;

import java.util.List;

public interface ApiClient {
    CurrencyResponse getCurrencies();

    RatesResponse getRates();
}
