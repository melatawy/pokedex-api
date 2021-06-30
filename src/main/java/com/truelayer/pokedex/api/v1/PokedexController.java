package com.truelayer.pokedex.api.v1;

import com.truelayer.pokedex.lib.PokemonRetriever;
import com.truelayer.pokedex.lib.PokemonTranslator;
import com.truelayer.pokedex.pojos.PokemonOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/pokemon")
public class PokedexController {
    private final RestTemplate restTemplate;
    private final PokemonRetriever pokemonRetriever;
    private final PokemonTranslator pokemonTranslator;

    public PokedexController(RestTemplate restTemplate, PokemonRetriever pokemonRetriever, PokemonTranslator pokemonTranslator) {
        this.restTemplate = restTemplate;
        this.pokemonRetriever = pokemonRetriever;
        this.pokemonTranslator = pokemonTranslator;
    }

    @GetMapping("/{name}")
    public PokemonOutput getPokemon(@PathVariable String name) {
        return pokemonRetriever.execute(name);
    }

    @GetMapping("/translated/{name}")
    public PokemonOutput getTranslatedPokemon(@PathVariable String name) {
        return pokemonTranslator.execute(name);
    }
}
