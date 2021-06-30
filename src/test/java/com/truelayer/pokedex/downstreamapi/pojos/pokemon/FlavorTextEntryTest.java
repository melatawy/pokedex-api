package com.truelayer.pokedex.downstreamapi.pojos.pokemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlavorTextEntryTest {

    @Test
    public void getFlavorTextShouldSanitiseText() {
        FlavorTextEntry flavorTextEntry = new FlavorTextEntry();
        flavorTextEntry.setFlavorText("I love\nto code\fwhat about you");
        assertEquals(flavorTextEntry.getFlavorText(), "I love to code what about you");
    }
}