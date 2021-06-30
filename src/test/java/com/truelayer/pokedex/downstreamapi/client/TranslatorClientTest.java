package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TranslatorClientTest {
    private RestTemplate restTemplate;
    private TranslatorClient translatorClient;
    private TranslationResponse translationResponseResult;

    @BeforeEach
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        translationResponseResult = mock(TranslationResponse.class);
        when(restTemplate.postForObject(anyString(), any(Map.class), any())).thenReturn(translationResponseResult);
        translatorClient = new TranslatorClient(restTemplate);
    }

    @Test
    public void callUsesShakespearByDefault() {
        TranslationResponse result = translatorClient.call("text to translate");

        assertEquals(translationResponseResult, result);
        ArgumentCaptor<Map> argumentCaptor = ArgumentCaptor.forClass(Map.class);
        verify(restTemplate).postForObject(
                eq("https://api.funtranslations.com/translate/shakespeare"),
                argumentCaptor.capture(),
                eq(TranslationResponse.class));
        assertEquals(argumentCaptor.getValue().get("text"), "text to translate");
    }

    @Test
    public void setTranslator() {
        translatorClient.setTranslator("yoda");
        TranslationResponse result = translatorClient.call("text to translate");

        assertEquals(translationResponseResult, result);
        ArgumentCaptor<Map> argumentCaptor = ArgumentCaptor.forClass(Map.class);
        verify(restTemplate).postForObject(
                eq("https://api.funtranslations.com/translate/yoda"),
                argumentCaptor.capture(),
                eq(TranslationResponse.class));
        assertEquals(argumentCaptor.getValue().get("text"), "text to translate");
    }
}