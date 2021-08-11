package com.yanovski.exchangeapi.services.impl;

import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.repositories.ApiKeysRepository;
import com.yanovski.exchangeapi.services.ApiKeysService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApiKeysServiceImpl implements ApiKeysService {
    private final ApiKeysRepository apiKeysRepository;

    public List<ApiKey> getActiveApiKeys() {
        return apiKeysRepository.findAllByActiveTrue();
    }
}
