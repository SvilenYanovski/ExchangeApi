package com.yanovski.exchangeapi.config.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import javax.servlet.http.HttpServletRequest;

public class ApiAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String apiKeyHeader;

    public ApiAuthFilter(String apiKeyHeader) {
        this.apiKeyHeader = apiKeyHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(apiKeyHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}
