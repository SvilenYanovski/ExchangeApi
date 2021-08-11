package com.yanovski.exchangeapi.client.api_model;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyResponse {
    private Boolean success;
    private Map<String, String> symbols;
}
