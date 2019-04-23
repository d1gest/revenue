package com.dkob.revenue.services;

import com.dkob.revenue.model.ExchangeRates;
import com.dkob.revenue.model.RevenueRequest;
import org.springframework.stereotype.Service;

@Service
public class RevenueService {

    private ExchangeRateService exchangeRateService;
    private final double spread = 0.64;

    public RevenueService(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public double calculateRevenue(RevenueRequest revenueRequest) {
        ExchangeRates exchangeRates = exchangeRateService.getExchangeRates(revenueRequest.getDate());
        double ratesDiff = (exchangeRates.getTodayRate() - spread) - (exchangeRates.getOldRate() + spread);
        return ratesDiff * revenueRequest.getAmount();
    }
}
