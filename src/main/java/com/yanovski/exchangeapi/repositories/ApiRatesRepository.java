package com.yanovski.exchangeapi.repositories;

import com.yanovski.exchangeapi.entities.ApiCurrency;
import com.yanovski.exchangeapi.entities.ApiCurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRatesRepository extends JpaRepository<ApiCurrencyRate, Long> {
    ApiCurrencyRate findFirstByCurrencyOrderByCreatedAtDesc(ApiCurrency currency);
    List<ApiCurrencyRate> findAllByCurrency(ApiCurrency currency);
}
