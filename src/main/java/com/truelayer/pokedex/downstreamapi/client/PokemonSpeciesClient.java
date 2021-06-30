package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonSpeciesClient extends AbstractJsonClient implements PokedexClient<String, Species> {

    public PokemonSpeciesClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public Species call(String url) {
        return restTemplate.getForObject(url, Species.class);
    }
}