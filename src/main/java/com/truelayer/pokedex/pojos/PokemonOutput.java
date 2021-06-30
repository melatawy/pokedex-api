package com.truelayer.pokedex.pojos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PokemonOutput {
    String name;
    String description;
    String habitat;
    boolean isLegendary;
}
