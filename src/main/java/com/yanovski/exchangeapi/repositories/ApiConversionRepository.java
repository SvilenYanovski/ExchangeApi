package com.yanovski.exchangeapi.repositories;

import com.yanovski.exchangeapi.entities.ApiCurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ApiConversionRepository  extends JpaRepository<ApiCurrencyConversion, Long> {
    @Query(value = "SELECT * FROM api_currency_conversion WHERE created_at >= :startDate AND created_at <= :endDate", nativeQuery = true)
    List<ApiCurrencyConversion> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
