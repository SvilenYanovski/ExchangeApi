package com.yanovski.exchangeapi.client.api_model;

import lombok.Data;

import java.util.Map;

@Data
public class RatesResponse {
    private Boolean success;
    private Long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
