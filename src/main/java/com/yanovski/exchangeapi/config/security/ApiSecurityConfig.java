package com.yanovski.exchangeapi.config.security;

import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.services.ApiKeysService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ApiKeysService apiKeysService;

    @Value("${http.api-key-header}")
    private String apiKeyHeader;

    private List<String> activeKeys;

    private final static String[] SWAGGER_RESOURCES = {"/", "/csrf", "/v2/api-docs",
            "/swagger-resources/configuration/ui", "/configuration/ui", "/swagger-resources",
            "/swagger-resources/configuration/security", "/configuration/security",
            "/swagger-ui.html", "/webjars/**"};

    public ApiSecurityConfig(ApiKeysService apiKeysService) {
        this.apiKeysService = apiKeysService;
    }

    @PostConstruct
    public void init() {
        activeKeys = apiKeysService.getActiveApiKeys().stream()
                .map(ApiKey::getApiKey)
                .collect(Collectors.toList());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ApiAuthFilter filter = new ApiAuthFilter(apiKeyHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!activeKeys.contains(principal))
            {
                throw new BadCredentialsException("Invalid or missing api key.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                //Swagger whitelist
                .antMatchers(SWAGGER_RESOURCES).permitAll().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }
}