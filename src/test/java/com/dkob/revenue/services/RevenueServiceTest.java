package com.dkob.revenue.services;

import com.dkob.revenue.model.ExchangeRates;
import com.dkob.revenue.model.RevenueRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RevenueServiceTest {

    @Mock
    private ExchangeRateService exchangeRateService;
    @InjectMocks
    private RevenueService revenueService;

    @Test
    public void testCalculations() {
        RevenueRequest revenueRequest = new RevenueRequest();
        revenueRequest.setAmount(10);
        revenueRequest.setDate(new Date());

        ExchangeRates exchangeRates = new ExchangeRates(20.64, 9.36);
        when(exchangeRateService.getExchangeRates(any())).thenReturn(exchangeRates);


        Assert.assertEquals(100, revenueService.calculateRevenue(revenueRequest), 0.0001);
    }
}