package com.yanovski.exchangeapi.client.impl;

import com.yanovski.exchangeapi.client.ApiClient;
import com.yanovski.exchangeapi.client.api_model.CurrencyResponse;
import com.yanovski.exchangeapi.client.api_model.RatesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class FixerApiClient implements ApiClient {
    @Value("${fixer.io.api-key}")
    private String apiKey;

    private static final String BASE_API = "http://data.fixer.io/api/";
    private static final String CURRENCIES = "symbols";
    private static final String RATES = "latest";

    @Override
    public CurrencyResponse getCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_API + CURRENCIES;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("access_key", apiKey);

        log.info("Executing GET operation on URL: {}", builder.toUriString());
        ResponseEntity<CurrencyResponse> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                CurrencyResponse.class
        );
        return result.getBody();
    }

    @Override
    public RatesResponse getRates() {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_API + RATES;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("access_key", apiKey);

        log.info("Executing GET operation on URL: {}", builder.toUriString());
        ResponseEntity<RatesResponse> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                RatesResponse.class
        );
        return result.getBody();
    }
}
