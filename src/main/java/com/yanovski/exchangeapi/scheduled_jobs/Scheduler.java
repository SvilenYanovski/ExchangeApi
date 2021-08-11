package com.yanovski.exchangeapi.scheduled_jobs;

import com.yanovski.exchangeapi.dto.RateDto;
import com.yanovski.exchangeapi.services.CurrencyProxyService;
import com.yanovski.exchangeapi.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class Scheduler {

    private final CurrencyProxyService currencyProxyService;
    private final CurrencyService currencyService;

    @Scheduled(cron = "0 0 0,8,16 * * *")
    public void scheduleFixedDelayTask() {
        currencyService.saveRates(currencyProxyService.getRates());
    }
}
