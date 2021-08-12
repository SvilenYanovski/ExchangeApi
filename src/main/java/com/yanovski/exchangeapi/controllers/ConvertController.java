package com.yanovski.exchangeapi.controllers;

import com.yanovski.exchangeapi.dto.ConversionDto;
import com.yanovski.exchangeapi.services.ConversionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("convert")
@AllArgsConstructor
@Api(value="conversion", description="Conversion API endpoints")
public class ConvertController {
    private final ConversionService conversionService;

    @GetMapping("list")
    @ApiOperation(value = "Get all Conversion transactions.")
    public List<ConversionDto> getAll() {
        log.info("action=ConvertController.getAll");
        return conversionService.getAllConversions();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve Conversion by ID(numeric).")
    public ConversionDto getById(@PathVariable String id) {
        log.info("action=ConvertController.getById({})", id);
        return conversionService.getById(id);
    }

    @GetMapping("list/day/start/{fromDate}/end/{toDate}")
    @ApiOperation(value = "Get all Conversion transactions between dates (format = 22-05-2021)")
    public List<ConversionDto> getConversionBetweenDates(@PathVariable String fromDate,
                                    @PathVariable String toDate) {
        log.info("action=ConvertController.getConversionBetweenDates({} to {})", fromDate, toDate);
        return conversionService.getConversionBetweenDates(fromDate, toDate);
    }

    @GetMapping("rate/from/{fromCode}/to/{toCode}")
    @ApiOperation(value = "Get a cross rate for conversion between two currency codes (format = EUR)")
    public Double getConversionRate(@PathVariable String fromCode,
                                          @PathVariable String toCode) {
        log.info("action=ConvertController.getConversionRate({} to {})", fromCode, toCode);
        return conversionService.getConversionRate(fromCode, toCode);
    }

    @PostMapping("from/{fromCode}/to/{toCode}/amount/{amount}")
    @ApiOperation(value = "Create conversion between two currency symbols (format = EUR)")
    public ConversionDto createConversion(@PathVariable String fromCode,
                                          @PathVariable String toCode,
                                          @PathVariable String amount) {
        log.info("action=ConvertController.createConversion({} to {}, amount {})", fromCode, toCode, amount);
        return conversionService.createConversion(fromCode, toCode, amount);
    }
}
