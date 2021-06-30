package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PokemonClientTest {

    @Test
    public void call() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        Pokemon pokemonResult = mock(Pokemon.class);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(pokemonResult);
        PokemonClient pokemonClient = new PokemonClient(restTemplate);

        Pokemon result = pokemonClient.call("pokemonName");

        assertEquals(pokemonResult, result);
        verify(restTemplate).getForObject("https://pokeapi.co/api/v2/pokemon/pokemonName", Pokemon.class);
    }
}