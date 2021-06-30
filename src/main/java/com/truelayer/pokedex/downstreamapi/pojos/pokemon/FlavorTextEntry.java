package com.truelayer.pokedex.downstreamapi.pojos.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorTextEntry {
    @JsonProperty("flavor_text")
    private String flavorText;
    private Language language;

    public String getFlavorText() {
        return this.flavorText
                .replaceAll("\\n", " ")
                .replaceAll("\\f", " ");
    }
}
