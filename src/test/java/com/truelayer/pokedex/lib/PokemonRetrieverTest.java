package com.truelayer.pokedex.lib;

import com.truelayer.pokedex.downstreamapi.client.PokemonClient;
import com.truelayer.pokedex.downstreamapi.client.PokemonSpeciesClient;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import com.truelayer.pokedex.pojos.PokemonOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokemonRetrieverTest {

    private PokemonClient pokemonClient;
    private PokemonSpeciesClient pokemonSpeciesClient;
    private Pokemon pokemon;
    private Species species;

    @BeforeEach
    public void setUp() {
        pokemonClient = Mockito.mock(PokemonClient.class);
        pokemonSpeciesClient = Mockito.mock(PokemonSpeciesClient.class);

        pokemon = PokemonLibHelpers.preparePokemonTestData();
        species = PokemonLibHelpers.preparePokemonSpeciesTestData("cave", true);

        Mockito.when(pokemonClient.call("pokemonName")).thenReturn(pokemon);
        Mockito.when(pokemonSpeciesClient.call("/url/to/pokemon/species")).thenReturn(species);
    }

    @Test
    public void execute() {
        PokemonRetriever pokemonRetriever = new PokemonRetriever(pokemonClient, pokemonSpeciesClient);

        PokemonOutput pokemonOutput = pokemonRetriever.execute("pokemonName");

        assertEquals(pokemonOutput.getName(), "POKEMON_NAME");
        assertEquals(pokemonOutput.getDescription(), "This is the english text");
        assertEquals(pokemonOutput.getHabitat(), "cave");
        assertTrue(pokemonOutput.isLegendary());
    }
}