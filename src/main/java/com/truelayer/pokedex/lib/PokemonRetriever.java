package com.truelayer.pokedex.lib;

import com.truelayer.pokedex.downstreamapi.client.PokemonClient;
import com.truelayer.pokedex.downstreamapi.client.PokemonSpeciesClient;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.FlavorTextEntry;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import com.truelayer.pokedex.pojos.PokemonOutput;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PokemonRetriever {

    private final PokemonClient pokemonClient;
    private final PokemonSpeciesClient pokemonSpeciesClient;

    public PokemonRetriever(PokemonClient pokemonClient, PokemonSpeciesClient pokemonSpeciesClient) {
        this.pokemonClient = pokemonClient;
        this.pokemonSpeciesClient = pokemonSpeciesClient;
    }

    public PokemonOutput execute(String name) {
        Pokemon pokemon = pokemonClient.call(name);
        Species species = pokemonSpeciesClient.call(pokemon.getSpecies().getUrl());
        return PokemonOutput.builder()
                .name(pokemon.getName())
                .description(getDescription(species))
                .habitat(species.getHabitat().getName())
                .isLegendary(species.isLegendary())
                .build();
    }

    protected String getDescription(Species species) {
        return getEnglishDescription(species);
    }

    protected String getEnglishDescription(@NotNull Species species) {
        for (FlavorTextEntry entry : species.getFlavorTextEntries()) {
            if (entry.getLanguage().getName().equals("en")) return entry.getFlavorText();
        }
        return "";
    }
}
