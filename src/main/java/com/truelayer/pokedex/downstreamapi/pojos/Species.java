package com.truelayer.pokedex.downstreamapi.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Species {
    Habitat habitat;
    @JsonProperty("flavor_text_entries")
    FlavorTextEntry[] flavorTextEntries;
    @JsonProperty("is_legendary")
    boolean isLegendary;
}
