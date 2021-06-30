package com.truelayer.pokedex.downstreamapi.pojos.translator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationResponse {
    private TranslationSuccess success;
    private TranslationContent contents;
}
