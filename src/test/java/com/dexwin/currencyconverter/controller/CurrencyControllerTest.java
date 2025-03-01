package com.dexwin.currencyconverter.controller;

import com.dexwin.currencyconverter.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;
    
 
    @Test
    void should_convert_EUR_to_USD_with_rate_greater_than_1() throws Exception {
        when(currencyService.convert(eq("EUR"), eq("USD"), anyDouble())).thenReturn(1.1);

        mockMvc.perform(get("/currencies/convert")
                .param("source", "EUR")
                .param("target", "USD")
                .param("amount", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1.1"));
    }
    
 @Test
    void should_convert_USD_to_EUR_with_rate_less_than_1() throws Exception {
        // Mock the conversion rate for USD to EUR
        when(currencyService.convert(eq("USD"), eq("EUR"), anyDouble())).thenReturn(0.9);

        mockMvc.perform(get("/currencies/convert")
                .param("source", "USD")
                .param("target", "EUR")
                .param("amount", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.9"));
    }

    
    @Test
    void shouldReturnBadRequestForNegativeAmount() throws Exception {
        mockMvc.perform(get("/currencies/convert")
                .param("source", "EUR")
                .param("target", "USD")
                .param("amount", "-5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Amount must be non-negative."));
    }

    @Test
    void shouldReturnBadRequestForNonEURSourceCurrency() throws Exception {
        when(currencyService.convert(eq("GBP"), eq("USD"), anyDouble()))
                .thenThrow(new IllegalArgumentException("Only EUR as source currency is supported"));

        mockMvc.perform(get("/currencies/convert")
                .param("source", "GBP")
                .param("target", "USD")
                .param("amount", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Only EUR as source currency is supported"));
    }

    @Test
    void shouldReturnBadRequestForInvalidCurrency() throws Exception {
        when(currencyService.convert(eq("EUR"), eq("INVALID"), anyDouble()))
                .thenThrow(new IllegalArgumentException("Exchange rate for INVALID not found"));

        mockMvc.perform(get("/currencies/convert")
                .param("source", "EUR")
                .param("target", "INVALID")
                .param("amount", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Exchange rate for INVALID not found"));
    }
}
