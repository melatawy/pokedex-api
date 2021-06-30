package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationRequest;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class TranslatorClient extends AbstractJsonClient implements PokedexClient<TranslationRequest, TranslationResponse> {
    private final String API_ENDPOINT = "https://api.funtranslations.com/translate/";

    public TranslatorClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public TranslationResponse call(TranslationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        Map<String, String> body = new HashMap<>();
        body.put("text", request.getText());
        return restTemplate.exchange(API_ENDPOINT + request.getTranslator(), HttpMethod.POST, entity, TranslationResponse.class, body).getBody();
    }
}
