package com.truelayer.pokedex.downstreamapi.client;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class PokemonClient extends AbstractJsonClient implements PokedexClient<String, Pokemon> {
    private final String API_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public Pokemon call(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(API_ENDPOINT+name, HttpMethod.GET, entity, Pokemon.class).getBody();
    }
}
