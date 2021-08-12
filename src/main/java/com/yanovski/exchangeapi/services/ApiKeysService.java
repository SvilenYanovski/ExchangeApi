package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.ApiKeyDto;

import java.util.List;

/**
 * Service to provide methods for Api Keys
 */
public interface ApiKeysService {
    /**
     * Retrieve a list with the active api keys
     *
     * @return api key list
     */
    List<ApiKeyDto> getActiveApiKeys();

    /**
     * Get api key by ID
     *
     * @param id api key id
     * @return dto object with the key value
     */
    ApiKeyDto getById(Long id);
}
