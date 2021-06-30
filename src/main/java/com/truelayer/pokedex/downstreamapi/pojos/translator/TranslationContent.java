package com.truelayer.pokedex.downstreamapi.pojos.translator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationContent {
    private String translated;
    private String text;
    private String translation;
}
