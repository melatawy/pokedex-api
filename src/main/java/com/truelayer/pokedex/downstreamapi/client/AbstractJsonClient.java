package com.truelayer.pokedex.downstreamapi.client;

import org.springframework.web.client.RestTemplate;

public abstract class AbstractJsonClient {
    protected final RestTemplate restTemplate;
    public AbstractJsonClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
