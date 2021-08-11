package com.yanovski.exchangeapi.repositories;

import com.yanovski.exchangeapi.entities.ApiCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCurrencyRepository extends JpaRepository<ApiCurrency, Long> {
    ApiCurrency findByCode(String code);
}
