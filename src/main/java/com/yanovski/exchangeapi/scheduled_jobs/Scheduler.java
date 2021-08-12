package com.yanovski.exchangeapi.scheduled_jobs;

import com.yanovski.exchangeapi.dto.RateDto;
import com.yanovski.exchangeapi.services.CurrencyProxyService;
import com.yanovski.exchangeapi.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Provides functionality to run tasks on regular interval
 */
@Configuration
@EnableScheduling
@AllArgsConstructor
public class Scheduler {

    private final CurrencyProxyService currencyProxyService;
    private final CurrencyService currencyService;

    /**
     * We retrieve the latest exchange rates every 8 hours (midnight, 8AM, 16PM).
     * The fixer.io subscription provides only 250 calls, so we have no chance to call it every time.
     */
    @Scheduled(cron = "0 0 0,8,16 * * *")
    public void scheduleFixedDelayTask() {
        currencyService.saveRates(currencyProxyService.getRates());
    }
}
