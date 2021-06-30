package com.truelayer.pokedex.downstreamapi.pojos.translator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationSuccess {
    private int total;
}
