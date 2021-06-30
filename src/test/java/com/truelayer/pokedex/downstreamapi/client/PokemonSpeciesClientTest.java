package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PokemonSpeciesClientTest {

    @Test
    public void call() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        Species speciesResult = mock(Species.class);
        ResponseEntity<Species> speciesResponseEntity = mock(ResponseEntity.class);
        when(speciesResponseEntity.getBody()).thenReturn(speciesResult);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(speciesResponseEntity);
        PokemonSpeciesClient pokemonSpeciesClient = new PokemonSpeciesClient(restTemplate);

        Species result = pokemonSpeciesClient.call("/url/to/species");

        assertEquals(speciesResult, result);
        verify(restTemplate).exchange(eq("/url/to/species"), eq(HttpMethod.GET), any(HttpEntity.class), eq(Species.class));
    }
}