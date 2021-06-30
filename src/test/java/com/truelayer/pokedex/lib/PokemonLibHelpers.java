package com.truelayer.pokedex.lib;

import com.truelayer.pokedex.downstreamapi.pojos.pokemon.*;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationContent;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationResponse;
import com.truelayer.pokedex.downstreamapi.pojos.translator.TranslationSuccess;

public class PokemonLibHelpers {
    public static Pokemon preparePokemonTestData() {
        SpeciesReference speciesReference = new SpeciesReference();
        speciesReference.setUrl("/url/to/pokemon/species");
        Pokemon pokemon = new Pokemon();
        pokemon.setName("POKEMON_NAME");
        pokemon.setSpecies(speciesReference);

        return pokemon;
    }

    public static Species preparePokemonSpeciesTestData(String habitatName, boolean isLegendary) {
        Habitat habitat = new Habitat();
        habitat.setName(habitatName);

        Language englishLang = new Language();
        englishLang.setName("en");

        Language frenchLang = new Language();
        frenchLang.setName("fr");

        Language spanishLang = new Language();
        spanishLang.setName("es");

        FlavorTextEntry[] flavorTextEntries = new FlavorTextEntry[3];
        flavorTextEntries[0] = new FlavorTextEntry();
        flavorTextEntries[0].setFlavorText("This is the french text");
        flavorTextEntries[0].setLanguage(frenchLang);

        flavorTextEntries[1] = new FlavorTextEntry();
        flavorTextEntries[1].setFlavorText("This is the english text");
        flavorTextEntries[1].setLanguage(englishLang);

        flavorTextEntries[2] = new FlavorTextEntry();
        flavorTextEntries[2].setFlavorText("This is the spanish text");
        flavorTextEntries[2].setLanguage(spanishLang);

        Species species = new Species();
        species.setLegendary(isLegendary);
        species.setHabitat(habitat);
        species.setFlavorTextEntries(flavorTextEntries);

        return species;
    }

    public static TranslationResponse getTranslationResponse(boolean success, String translator) {
        TranslationSuccess translationSuccess = new TranslationSuccess();
        translationSuccess.setTotal(success ? 1 : 0);
        TranslationContent translationContent = new TranslationContent();
        translationContent.setText("Let's assume that is the test requested");
        translationContent.setTranslated("and that's the translation by " + translator);
        translationContent.setTranslation(translator);
        TranslationResponse response = new TranslationResponse();
        response.setContents(translationContent);
        response.setSuccess(translationSuccess);
        return response;
    }
}

