package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationRequest;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class TranslatorClient extends AbstractJsonClient implements PokedexClient<TranslationRequest, TranslationResponse> {
    private final String API_ENDPOINT = "https://api.funtranslations.com/translate/";
    private final String DEFAULT_TRANSLATOR = "shakespeare";

    public TranslatorClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public TranslationResponse call(TranslationRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("text", request.getText());
        return restTemplate.postForObject(API_ENDPOINT + request.getTranslator(), body, TranslationResponse.class);
    }
}
