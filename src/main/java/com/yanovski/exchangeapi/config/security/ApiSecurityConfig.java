package com.yanovski.exchangeapi.config.security;

import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.services.ApiKeysService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${http.auth-token-header-name}")
    private String principalRequestHeader;

    private List<String> activeKeys;

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
        ApiAuthFilter filter = new ApiAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!activeKeys.contains(principal))
            {
                throw new BadCredentialsException("The API key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });
        httpSecurity.
                antMatcher("/**").
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }
}