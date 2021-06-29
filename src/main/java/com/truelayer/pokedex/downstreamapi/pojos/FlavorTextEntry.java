package com.truelayer.pokedex.downstreamapi.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorTextEntry {
    @JsonProperty("flavor_text")
    String flavorText;
    Language language;
}
