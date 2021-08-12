package com.yanovski.exchangeapi.services.impl;

import com.yanovski.exchangeapi.config.api_errors.exceptions.ApiEntityNotFoundException;
import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.repositories.ApiKeysRepository;
import com.yanovski.exchangeapi.services.ApiKeysService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApiKeysServiceImpl implements ApiKeysService {
    private final ApiKeysRepository apiKeysRepository;

    @Override
    public List<ApiKeyDto> getActiveApiKeys() {
        return apiKeysRepository.findAllByActiveTrue().stream()
                .map(ApiKeyDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ApiKeyDto getById(Long id) {
        ApiKey result = apiKeysRepository.findById(id)
                .orElseThrow(() -> new ApiEntityNotFoundException(ApiKey.class, "id", id.toString()));
        return new ApiKeyDto(result);
    }
}
