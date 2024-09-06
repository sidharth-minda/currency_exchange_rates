package com.arenema.demo.services;

import com.arenema.demo.dtos.ConversionRate;
import com.arenema.demo.dtos.CurrencyConversionResponse;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyConversionService {

    @Autowired
    private HttpService httpService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionService.class);

    public ResponseEntity<CurrencyConversionResponse> currencyConverter(String sourceCurrency, String targetCurrency) {
        LocalDate today = LocalDate.now();
        LocalDate exchangeDate = today.minusDays(1);
        CurrencyConversionResponse currencyConversionResponse = new CurrencyConversionResponse("USD");
        for(int i = 1; i <= 3; i++) {
            String url = "https://api.frankfurter.app/" + exchangeDate + "?from=" + sourceCurrency + "&to=" + targetCurrency;
            Map<String, Object> apiResponse = httpService.getExchangeRate(url);
            LOGGER.info(apiResponse + "        apiResponse");
            parseResponse(apiResponse, currencyConversionResponse);
            exchangeDate = exchangeDate.minusDays(1);
        }
        return new ResponseEntity<>(currencyConversionResponse, HttpStatus.OK);
    }

    public void parseResponse(Map<String, Object> response, CurrencyConversionResponse currencyConversionResponse) {
        Map<String, Double> exchangeRates = (Map<String, Double>) response.get("rates");
        LOGGER.info(exchangeRates + "     exchangeRates");
        List<ConversionRate> conversionRateList = exchangeRates.entrySet().stream().map(entry -> new ConversionRate(entry.getKey(), entry.getValue().toString())).toList();
        conversionRateList.forEach(conversionRate -> {
            LOGGER.info(conversionRate.getTarget() + "   conversionRate.getTarget()");
            LOGGER.info(conversionRate.getValue() + "   conversionRate.getValue()");
            currencyConversionResponse.addRate(response.get("date").toString(), conversionRate);
        });
    }
}
