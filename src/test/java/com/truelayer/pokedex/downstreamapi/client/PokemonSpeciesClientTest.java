package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import org.junit.jupiter.api.Test;
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
        when(restTemplate.getForObject(anyString(), any())).thenReturn(speciesResult);
        PokemonSpeciesClient pokemonSpeciesClient = new PokemonSpeciesClient(restTemplate);

        Species result = pokemonSpeciesClient.call("/url/to/species");

        assertEquals(speciesResult, result);
        verify(restTemplate).getForObject("/url/to/species", Species.class);
    }
}