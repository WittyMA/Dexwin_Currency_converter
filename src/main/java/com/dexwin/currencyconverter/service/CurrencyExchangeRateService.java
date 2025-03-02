package com.dexwin.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * TODO: Implementation of this class has to be backed by https://api.exchangerate.host/latest?base=EUR&symbols=AUD,CAD,CHF,CNY,GBP,JPY,USD
 */
@Service
public class CurrencyExchangeRateService implements CurrencyService {
    private static final String BASE_URL = "https://api.exchangerate.host/latest?base=EUR";
    private final RestTemplate restTemplate;

    public CurrencyExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double convert(String source, String target, double amount) {
        if (!"EUR".equals(source)) {
            throw new IllegalArgumentException("Only EUR as source currency is supported");
        }

        String url = BASE_URL + "&symbols=" + target;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
        if (response == null || response.getRates() == null || !response.getRates().containsKey(target)) {
            throw new RuntimeException("Failed to fetch exchange rates");
        }

        double rate = response.getRates().get(target);
        return amount * rate;
    }
}
