package com.arenema.demo.controllers;

import com.arenema.demo.dtos.CurrencyConversionResponse;
import com.arenema.demo.services.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/fx/{targetCurrency}")
    public ResponseEntity<CurrencyConversionResponse> currencyConverter(@PathVariable("targetCurrency") String targetCurrency) {
        return currencyConversionService.currencyConverter("USD", targetCurrency);
    }
}
