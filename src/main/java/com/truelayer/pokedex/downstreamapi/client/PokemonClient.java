package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonClient extends AbstractJsonClient implements PokedexClient<String, Pokemon> {
    private final String API_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public Pokemon call(String name) {
        return restTemplate.getForObject(API_ENDPOINT+name, Pokemon.class);
    }
}
