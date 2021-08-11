package com.yanovski.exchangeapi.repositories;

import com.yanovski.exchangeapi.entities.ApiCurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiConversionRepository  extends JpaRepository<ApiCurrencyConversion, Long> {
}
