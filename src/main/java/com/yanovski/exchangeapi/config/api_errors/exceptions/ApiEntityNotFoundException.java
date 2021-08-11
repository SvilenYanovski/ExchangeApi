package com.yanovski.exchangeapi.config.api_errors.exceptions;

import com.yanovski.exchangeapi.utils.ApiUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

public class ApiEntityNotFoundException  extends RuntimeException {

    public ApiEntityNotFoundException(Class clazz, String... searchParamsMap) {
        super(ApiEntityNotFoundException.generateMessage(clazz.getSimpleName(),
                ApiUtils.toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + " not found for parameters " + searchParams;
    }
}
