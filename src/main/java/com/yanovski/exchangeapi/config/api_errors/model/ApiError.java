package com.yanovski.exchangeapi.config.api_errors.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String label;
    private String message;
}
