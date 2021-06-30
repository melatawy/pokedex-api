package com.truelayer.pokedex.downstreamapi.pojos.translator;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TranslationRequest {
    private String text;
    private String translator;

    public String getTranslator() {
        return (translator == null) ? "shakespeare" : translator;
    }
}
