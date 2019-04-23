package com.dkob.revenue.services;

import com.dkob.revenue.model.ExchangeRates;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExchangeRateService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String baseUrl = "https://api.exchangeratesapi.io/";

    public ExchangeRateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    public ExchangeRates getExchangeRates(Date date) {
        final String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String oldDate = simpleDateFormat.format(date);

        String url = baseUrl + oldDate + "?symbols=RUB&base=USD&format=1";
        ResponseEntity<String> oldRateResponse = restTemplate.getForEntity(url, String.class);
        double oldRate = getRate(oldRateResponse);

        url = baseUrl + "latest?symbols=RUB&base=USD&format=1";
        ResponseEntity<String> newRateResponse = restTemplate.getForEntity(url, String.class);
        double newRate = getRate(newRateResponse);

        return new ExchangeRates(newRate, oldRate);
    }

    private double getRate(ResponseEntity<String> response) {
        double rate = 0;
        try {
            JsonNode rates = objectMapper.readTree(response.getBody()).get("rates");
            rate = rates.get("RUB").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
