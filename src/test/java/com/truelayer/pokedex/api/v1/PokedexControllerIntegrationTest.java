package com.truelayer.pokedex.api.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PokedexControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPokemonDitto() throws Exception {
        this.mockMvc.perform(get("/pokemon/ditto")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"name\":\"ditto\",\"description\":\"It can freely recombine its own cellular structure to transform into other life-forms.\",\"habitat\":\"urban\",\"legendary\":false}"
                ));

    }

    @Test
    public void getPokemonMewtwo() throws Exception {
        this.mockMvc.perform(get("/pokemon/mewtwo")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"name\":\"mewtwo\",\"description\":\"It was created by a scientist after years of horrific gene splicing and DNA engineering experiments.\",\"habitat\":\"rare\",\"legendary\":true}"
                ));

    }

    @Test
    public void getTranslatedPokemonDitto() throws Exception {
        this.mockMvc.perform(get("/pokemon/translated/ditto")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"name\":\"ditto\",\"description\":\"'t can freely recombine its own cellular structure to transform into other life-forms.\",\"habitat\":\"urban\",\"legendary\":false}"
                ));
    }

    @Test
    public void getTranslatedPokemonMewtwo() throws Exception {
        this.mockMvc.perform(get("/pokemon/translated/mewtwo")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"name\":\"mewtwo\",\"description\":\"Created by a scientist after years of horrific gene splicing and dna engineering experiments,  it was.\",\"habitat\":\"rare\",\"legendary\":true}"
                ));
    }
}