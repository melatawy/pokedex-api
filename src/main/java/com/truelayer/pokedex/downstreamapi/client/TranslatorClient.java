package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class TranslatorClient extends AbstractJsonClient implements PokedexClient<String, TranslationResponse> {
    @Setter
    private String translator;
    private final String API_ENDPOINT = "https://api.funtranslations.com/translate/";

    public TranslatorClient(RestTemplate restTemplate) {
        super(restTemplate);

        translator = "shakespeare";
    }

    public TranslationResponse call(String text) {
        Map<String, String> body = new HashMap<>();
        body.put("text", text);
        return restTemplate.postForObject(API_ENDPOINT+translator, body, TranslationResponse.class);
    }
}
