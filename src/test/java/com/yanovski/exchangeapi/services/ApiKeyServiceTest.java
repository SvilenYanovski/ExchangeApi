package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.config.api_errors.exceptions.ApiEntityNotFoundException;
import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.repositories.ApiKeysRepository;
import com.yanovski.exchangeapi.services.impl.ApiKeysServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApiKeyServiceTest {
    private ApiKeysService apiKeysService;
    private final ApiKeysRepository apiKeysRepository = Mockito.mock(ApiKeysRepository.class);

    private final List<ApiKey> keys = new ArrayList<>();

    @BeforeEach
    void init() {
        apiKeysService = new ApiKeysServiceImpl(apiKeysRepository);
        keys.add(ApiKey.builder().apiKey("key1").active(true).description("k1descr").id(1L).build());
        keys.add(ApiKey.builder().apiKey("key2").active(true).description("k2descr").id(2L).build());
        keys.add(ApiKey.builder().apiKey("key3").active(false).description("k3descr").id(3L).build());
    }

    @Test
    void testServiceCreated() {
        assertNotNull(apiKeysService);
    }

    @Test
    void testGetActiveKeyList() {
        when(apiKeysRepository.findAllByActiveTrue()).thenReturn(keys.stream().filter(ApiKey::getActive).collect(Collectors.toList()));
        List<ApiKeyDto> keyDtos = apiKeysService.getActiveApiKeys();

        assertNotNull(keyDtos);
        assertEquals(2, keyDtos.size());
    }

    @Test
    void testGetById() {
        when(apiKeysRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(keys.get(0)));
        ApiKeyDto key = apiKeysService.getById(1L);

        assertNotNull(key);
        assertNotNull(key.getId());
    }

    @Test
    void testGetByIdThrowsExceptionEntityNotFound() {
        when(apiKeysRepository.findById(any())).thenThrow(new ApiEntityNotFoundException(ApiKey.class, "id", "di"));
        assertThrows(ApiEntityNotFoundException.class, () -> apiKeysService.getById(5L));
    }
}
