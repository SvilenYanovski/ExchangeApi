package com.yanovski.exchangeapi.controllers;

import com.yanovski.exchangeapi.dto.ApiKeyDto;
import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.services.ApiKeysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@Api(value="home", description="Exchange api documentation")
public class HomeController {
    private final ApiKeysService apiKeysService;

    @GetMapping("/")
    @ApiOperation(value = "Home endpoint, redirect to Swagger documentation.")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("all")
    @ApiOperation(value = "Test endpoint to list all api keys", response = Iterable.class)
    public List<ApiKeyDto> all() {
        return apiKeysService.getActiveApiKeys();
    }

    @GetMapping("key/{id}")
    @ApiOperation(value = "Test endpoint to get api key by id", response = ApiKey.class)
    public ApiKeyDto getById(@PathVariable String id) {
        return apiKeysService.getById(Long.parseLong(id));
    }
}
