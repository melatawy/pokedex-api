package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationRequest;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<TranslationResponse> translationResponseEntity = mock(ResponseEntity.class);
        when(translationResponseEntity.getBody()).thenReturn(translationResponseResult);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class))).thenReturn(translationResponseEntity);
        translatorClient = new TranslatorClient(restTemplate);
    }

    @Test
    public void callUsesShakespearByDefault() {
        TranslationRequest translationRequest = TranslationRequest.builder().text("text to translate").build();
        TranslationResponse result = translatorClient.call(translationRequest);

        assertEquals(translationResponseResult, result);
        ArgumentCaptor<Map<String, String>> argumentCaptor = ArgumentCaptor.forClass(Map.class);
        verify(restTemplate).exchange(
                eq("https://api.funtranslations.com/translate/shakespeare"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(TranslationResponse.class),
                argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().get("text"), "text to translate");
    }

    @Test
    public void callUsesTranslatorRequested() {
        TranslationRequest translationRequest = TranslationRequest.builder().text("text to translate").translator("blabla").build();
        TranslationResponse result = translatorClient.call(translationRequest);

        assertEquals(translationResponseResult, result);
        ArgumentCaptor<Map<String, String>> argumentCaptor = ArgumentCaptor.forClass(Map.class);
        verify(restTemplate).exchange(
                eq("https://api.funtranslations.com/translate/blabla"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(TranslationResponse.class),
                argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().get("text"), "text to translate");
    }
}