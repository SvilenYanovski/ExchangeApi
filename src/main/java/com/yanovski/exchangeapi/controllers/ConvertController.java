package com.yanovski.exchangeapi.controllers;

import com.yanovski.exchangeapi.dto.ConversionDto;
import com.yanovski.exchangeapi.dto.CurrencyDto;
import com.yanovski.exchangeapi.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("convert")
@AllArgsConstructor
public class ConvertController {
    private final CurrencyService currencyService;

    @GetMapping("list")
    public List<ConversionDto> getAll() {
        return currencyService.getAllConversions();
    }

    @PostMapping("from/{fromCode}/to/{toCode}/amount/{amount}")
    public ConversionDto createConversion(@PathVariable String fromCode,
                                          @PathVariable String toCode,
                                          @PathVariable String amount) {
        return currencyService.createConversion(fromCode, toCode, amount);
    }
}
