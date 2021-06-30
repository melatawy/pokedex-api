package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PokemonClientTest {

    @Test
    public void call() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        Pokemon pokemonResult = mock(Pokemon.class);
        ResponseEntity<Pokemon> pokemonResponseEntity = mock(ResponseEntity.class);
        when(pokemonResponseEntity.getBody()).thenReturn(pokemonResult);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(pokemonResponseEntity);
        PokemonClient pokemonClient = new PokemonClient(restTemplate);

        Pokemon result = pokemonClient.call("pokemonName");

        assertEquals(pokemonResult, result);
        verify(restTemplate).exchange(eq("https://pokeapi.co/api/v2/pokemon/pokemonName"), eq(HttpMethod.GET), any(HttpEntity.class), eq(Pokemon.class));
    }
}