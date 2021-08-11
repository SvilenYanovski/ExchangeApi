package com.yanovski.exchangeapi.controllers;

import com.yanovski.exchangeapi.entities.ApiKey;
import com.yanovski.exchangeapi.services.ApiKeysService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class HomeController {
    private final ApiKeysService apiKeysService;

    @GetMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("all")
    public List<ApiKey> all() {
        return apiKeysService.getActiveApiKeys();
    }
}
