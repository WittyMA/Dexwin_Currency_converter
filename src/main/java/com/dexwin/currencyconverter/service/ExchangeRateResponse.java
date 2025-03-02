/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Package declaration
package com.dexwin.currencyconverter.service;

// Import necessary classes
import java.util.Map;

/**
 * ExchangeRateResponse is a simple POJO (Plain Old Java Object) that represents
 * the response from an exchange rate API. It contains a map of currency codes
 * to their corresponding exchange rates relative to a base currency.
 *
 * @author yesulikplimits
 */
public class ExchangeRateResponse {

    // A map storing currency codes (e.g., "USD", "EUR") as keys and their
    // corresponding exchange rates as values.
    private Map<String, Double> rates;

    /**
     * Retrieves the map of exchange rates.
     *
     * @return A map where the keys are currency codes and the values are
     *         exchange rates.
     */
    public Map<String, Double> getRates() {
        return rates;
    }

    /**
     * Sets the map of exchange rates.
     *
     * @param rates A map where the keys are currency codes and the values are
     *              exchange rates.
     */
    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
