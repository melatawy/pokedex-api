package com.truelayer.pokedex.downstreamapi.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    String name;
    SpeciesReference species;
}
