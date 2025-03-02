package com.dexwin.currencyconverter.controller;

import com.dexwin.currencyconverter.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Indicates that this class is a Spring MVC controller where every method returns a domain object instead of a view.
@RestController
@RequestMapping("currencies")
public class CurrencyController {

    // Service that provides currency conversion logic.
    private final CurrencyService currencyService;

    // Constructor injection of the CurrencyService.
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    // Handles HTTP GET requests for currency conversion.
    @GetMapping("/currencies/convert")
    public double convertCurrency(
            // 'source' currency code (e.g., "USD").
            @RequestParam String source,
            // 'target' currency code (e.g., "EUR").
            @RequestParam String target,
            // Amount to be converted.
            @RequestParam double amount) {

        // Validate that the amount is non-negative.
        if (amount < 0) {
            // Throw an exception if the amount is negative.
            throw new IllegalArgumentException("Amount must be non-negative.");
        }

        // Perform the currency conversion using the service and return the result.
        return currencyService.convert(source, target, amount);
    }
}
