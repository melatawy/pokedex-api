package com.truelayer.pokedex.lib;

import com.truelayer.pokedex.downstreamapi.client.PokemonClient;
import com.truelayer.pokedex.downstreamapi.client.PokemonSpeciesClient;
import com.truelayer.pokedex.downstreamapi.client.TranslatorClient;
import com.truelayer.pokedex.downstreamapi.pojos.pokemon.Species;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationRequest;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import org.springframework.stereotype.Component;

@Component
public class PokemonTranslator extends PokemonRetriever {
    private final TranslatorClient translatorClient;

    public PokemonTranslator(PokemonClient pokemonClient, PokemonSpeciesClient pokemonSpeciesClient, TranslatorClient translatorClient) {
        super(pokemonClient, pokemonSpeciesClient);
        this.translatorClient = translatorClient;
    }

    @Override
    protected String getDescription(Species species) {
        String basicSanitisedDescription = super.getEnglishDescription(species);

        TranslationResponse translationResponse = translatorClient.call(
                TranslationRequest.builder()
                        .text(basicSanitisedDescription)
                        .translator(getCorrectTranslator(species))
                        .build());

        if (translationResponse.getSuccess().getTotal() == 0) {
            return basicSanitisedDescription;
        } else {
            return translationResponse.getContents().getTranslated();
        }
    }

    private String getCorrectTranslator(Species species) {
        return (species.isLegendary() || species.getHabitat().getName().equals("cave")) ? "yoda" : "shakespeare";
    }
}
