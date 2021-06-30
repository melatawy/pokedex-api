package com.truelayer.pokedex.downstreamapi.pojos.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Habitat {
    private String name;
}
