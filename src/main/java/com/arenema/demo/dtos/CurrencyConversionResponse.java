package com.arenema.demo.dtos;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConversionResponse {

    private String source;
    private Map<String, Object> rates;

    public CurrencyConversionResponse(String source) {
        this.source = source;
        this.rates = new HashMap<>();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void addRate(String key, ConversionRate conversionRate) {
        this.rates.put(key, conversionRate);
    }

    public Map<String, Object> getRates() {
        return rates;
    }
}
