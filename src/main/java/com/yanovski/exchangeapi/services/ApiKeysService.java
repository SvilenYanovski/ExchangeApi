package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.entities.ApiKey;

import java.util.List;

public interface ApiKeysService {
    List<ApiKeyDto> getActiveApiKeys();

    ApiKeyDto getById(Long id);
}
