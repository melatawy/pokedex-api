package com.truelayer.pokedex.lib;

import com.truelayer.pokedex.downstreamapi.client.PokemonClient;
import com.truelayer.pokedex.downstreamapi.client.PokemonSpeciesClient;
import com.truelayer.pokedex.downstreamapi.client.TranslatorClient;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Pokemon;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationRequest;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import com.truelayer.pokedex.pojos.PokemonOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTranslatorTest {

    private PokemonClient pokemonClient;
    private PokemonSpeciesClient pokemonSpeciesClient;
    private TranslatorClient translatorClient;
    private Pokemon pokemon;
    private Species species;
    private TranslationResponse translationResponse;

    @BeforeEach
    public void setUp() {
        pokemonClient = Mockito.mock(PokemonClient.class);
        pokemonSpeciesClient = Mockito.mock(PokemonSpeciesClient.class);
        translatorClient = Mockito.mock(TranslatorClient.class);
    }

    @Test
    public void executeTranslateToYodaForHabitat() {
        pokemon = PokemonLibHelpers.preparePokemonTestData();
        species = PokemonLibHelpers.preparePokemonSpeciesTestData("cave", false);
        translationResponse = PokemonLibHelpers.getTranslationResponse(true, "yoda");

        Mockito.when(pokemonClient.call("pokemonName")).thenReturn(pokemon);
        Mockito.when(pokemonSpeciesClient.call("/url/to/pokemon/species")).thenReturn(species);
        Mockito.when(translatorClient.call(Mockito.any(TranslationRequest.class))).thenReturn(translationResponse);

        PokemonTranslator pokemonTranslator = new PokemonTranslator(pokemonClient, pokemonSpeciesClient, translatorClient);

        PokemonOutput pokemonOutput = pokemonTranslator.execute("pokemonName");

        assertEquals(pokemonOutput.getName(), "POKEMON_NAME");
        assertEquals(pokemonOutput.getDescription(), "and that's the translation by yoda");
        assertEquals(pokemonOutput.getHabitat(), "cave");
        assertFalse(pokemonOutput.isLegendary());

        ArgumentCaptor<TranslationRequest> translationRequestCaptor = ArgumentCaptor.forClass(TranslationRequest.class);
        Mockito.verify(translatorClient).call(translationRequestCaptor.capture());
        assertEquals(translationRequestCaptor.getValue().getText(), "This is the english text");
        assertEquals(translationRequestCaptor.getValue().getTranslator(), "yoda");
    }

    @Test
    public void executeTranslateToYodaForLegendary() {
        pokemon = PokemonLibHelpers.preparePokemonTestData();
        species = PokemonLibHelpers.preparePokemonSpeciesTestData("another", true);
        translationResponse = PokemonLibHelpers.getTranslationResponse(true, "yoda");

        Mockito.when(pokemonClient.call("pokemonName")).thenReturn(pokemon);
        Mockito.when(pokemonSpeciesClient.call("/url/to/pokemon/species")).thenReturn(species);
        Mockito.when(translatorClient.call(Mockito.any(TranslationRequest.class))).thenReturn(translationResponse);

        PokemonTranslator pokemonTranslator = new PokemonTranslator(pokemonClient, pokemonSpeciesClient, translatorClient);

        PokemonOutput pokemonOutput = pokemonTranslator.execute("pokemonName");

        assertEquals(pokemonOutput.getName(), "POKEMON_NAME");
        assertEquals(pokemonOutput.getDescription(), "and that's the translation by yoda");
        assertEquals(pokemonOutput.getHabitat(), "another");
        assertTrue(pokemonOutput.isLegendary());

        ArgumentCaptor<TranslationRequest> translationRequestCaptor = ArgumentCaptor.forClass(TranslationRequest.class);
        Mockito.verify(translatorClient).call(translationRequestCaptor.capture());
        assertEquals(translationRequestCaptor.getValue().getText(), "This is the english text");
        assertEquals(translationRequestCaptor.getValue().getTranslator(), "yoda");
    }

    @Test
    public void executeTranslateToShakespeareOtherwise() {
        pokemon = PokemonLibHelpers.preparePokemonTestData();
        species = PokemonLibHelpers.preparePokemonSpeciesTestData("another", false);
        translationResponse = PokemonLibHelpers.getTranslationResponse(true, "shakespeare");

        Mockito.when(pokemonClient.call("pokemonName")).thenReturn(pokemon);
        Mockito.when(pokemonSpeciesClient.call("/url/to/pokemon/species")).thenReturn(species);
        Mockito.when(translatorClient.call(Mockito.any(TranslationRequest.class))).thenReturn(translationResponse);

        PokemonTranslator pokemonTranslator = new PokemonTranslator(pokemonClient, pokemonSpeciesClient, translatorClient);

        PokemonOutput pokemonOutput = pokemonTranslator.execute("pokemonName");

        assertEquals(pokemonOutput.getName(), "POKEMON_NAME");
        assertEquals(pokemonOutput.getDescription(), "and that's the translation by shakespeare");
        assertEquals(pokemonOutput.getHabitat(), "another");
        assertFalse(pokemonOutput.isLegendary());

        ArgumentCaptor<TranslationRequest> translationRequestCaptor = ArgumentCaptor.forClass(TranslationRequest.class);
        Mockito.verify(translatorClient).call(translationRequestCaptor.capture());
        assertEquals(translationRequestCaptor.getValue().getText(), "This is the english text");
        assertEquals(translationRequestCaptor.getValue().getTranslator(), "shakespeare");
    }

    @Test
    public void executeFallbackToEnglishIfTranslationFailed() {
        pokemon = PokemonLibHelpers.preparePokemonTestData();
        species = PokemonLibHelpers.preparePokemonSpeciesTestData("another", false);
        translationResponse = PokemonLibHelpers.getTranslationResponse(false, "shakespeare");

        Mockito.when(pokemonClient.call("pokemonName")).thenReturn(pokemon);
        Mockito.when(pokemonSpeciesClient.call("/url/to/pokemon/species")).thenReturn(species);
        Mockito.when(translatorClient.call(Mockito.any(TranslationRequest.class))).thenReturn(translationResponse);

        PokemonTranslator pokemonTranslator = new PokemonTranslator(pokemonClient, pokemonSpeciesClient, translatorClient);

        PokemonOutput pokemonOutput = pokemonTranslator.execute("pokemonName");

        assertEquals(pokemonOutput.getName(), "POKEMON_NAME");
        assertEquals(pokemonOutput.getDescription(), "This is the english text");
        assertEquals(pokemonOutput.getHabitat(), "another");
        assertFalse(pokemonOutput.isLegendary());

        ArgumentCaptor<TranslationRequest> translationRequestCaptor = ArgumentCaptor.forClass(TranslationRequest.class);
        Mockito.verify(translatorClient).call(translationRequestCaptor.capture());
        assertEquals(translationRequestCaptor.getValue().getText(), "This is the english text");
        assertEquals(translationRequestCaptor.getValue().getTranslator(), "shakespeare");
    }
}