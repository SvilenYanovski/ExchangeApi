package com.yanovski.exchangeapi.client;

import com.yanovski.exchangeapi.client.api_model.Currency;

import java.util.List;

public interface ApiClient {
    List<Currency> getCurrencies();
}
