package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.entities.ApiKey;

import java.util.List;

public interface ApiKeysService {
    List<ApiKey> getActiveApiKeys();
}
