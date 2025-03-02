// Package declaration
package com.dexwin.currencyconverter.service;

// Import necessary classes and annotations
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * CurrencyExchangeRateService is a service class that implements the CurrencyService interface.
 * It provides functionality to convert an amount from a source currency to a target currency
 * using exchange rates fetched from an external API.
 *
 * TODO: Implementation of this class has to be backed by
 * https://api.exchangerate.host/latest?base=EUR&symbols=AUD,CAD,CHF,CNY,GBP,JPY,USD
 */

@Service
public class CurrencyExchangeRateService implements CurrencyService {

    // Base URL of the exchange rate API with EUR as the base currency
    private static final String BASE_URL = "https://api.exchangerate.host/latest?base=EUR";

    // RestTemplate is used to make HTTP requests
    private final RestTemplate restTemplate;

    /**
     * Constructor for CurrencyExchangeRateService.
     *
     * @param restTemplate RestTemplate instance to perform HTTP requests
     */
    public CurrencyExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Converts a specified amount from the source currency to the target currency.
     *
     * @param source The source currency code (e.g., "EUR")
     * @param target The target currency code (e.g., "USD")
     * @param amount The amount to convert
     * @return The converted amount in the target currency
     * @throws IllegalArgumentException if the source currency is not "EUR"
     * @throws RuntimeException         if fetching exchange rates fails
     */
    @Override
    public double convert(String source, String target, double amount) {
        // Ensure that the source currency is EUR
        if (!"EUR".equals(source)) {
            throw new IllegalArgumentException("Only EUR as source currency is supported");
        }

        // Construct the URL to fetch exchange rates for the target currency
        String url = BASE_URL + "&symbols=" + target;

        // Make an HTTP GET request to the exchange rate API
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        // Validate the response and ensure it contains the target currency rate
        if (response == null || response.getRates() == null || !response.getRates().containsKey(target)) {
            throw new RuntimeException("Failed to fetch exchange rates");
        }

        // Retrieve the exchange rate for the target currency
        double rate = response.getRates().get(target);

        // Calculate and return the converted amount
        return amount * rate;
    }
}
