package com.dexwin.currencyconverter.controller;

import com.dexwin.currencyconverter.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies/convert")
    public double convertCurrency(
            @RequestParam String source,
            @RequestParam String target,
            @RequestParam double amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be non-negative.");
        }

        return currencyService.convert(source, target, amount);
    }
}
