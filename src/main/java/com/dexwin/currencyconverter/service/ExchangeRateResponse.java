/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dexwin.currencyconverter.service;

import java.util.Map;

/**
 *
 * @author yesulikplimits
 */

public class ExchangeRateResponse {
    private Map<String, Double> rates;

    // Getter and Setter
    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
