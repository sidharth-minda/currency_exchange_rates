package com.arenema.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HttpService {

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> getExchangeRate(String uri) {
        return restTemplate.getForObject(uri, Map.class);
    }
}
