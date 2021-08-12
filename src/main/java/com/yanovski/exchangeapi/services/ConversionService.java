package com.yanovski.exchangeapi.services;

import com.yanovski.exchangeapi.dto.ConversionDto;

import java.util.List;

/**
 * Service to provide functionality for currency conversion
 */
public interface ConversionService {
    /**
     * Create Conversion
     *
     * @param fromCode first pair currency code
     * @param toCode second pair currency code
     * @param amount
     * @return created object
     */
    ConversionDto createConversion(String fromCode, String toCode, String amount);

    /**
     * Retrieve all conversions
     *
     * @return list
     */
    List<ConversionDto> getAllConversions();

    /**
     * Calculates a conversion rate between two currencies without creating a Conversion
     *
     * @param fromCode first pair currency code
     * @param toCode second pair currency code
     * @return rate
     */
    Double getConversionRate(String fromCode, String toCode);

    /**
     * Get Conversion by ID
     *
     * @param id conversion id
     * @return conversion
     */
    ConversionDto getById(String id);

    /**
     * Retrieves all conversions between two dates
     *
     * @param fromDate start date
     * @param toDate end date
     * @return list
     */
    List<ConversionDto> getConversionBetweenDates(String fromDate, String toDate);
}
