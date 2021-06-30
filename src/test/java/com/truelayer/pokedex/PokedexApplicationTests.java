package com.truelayer.pokedex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PokedexApplicationTests {

    @Autowired
    private PokedexApplication application;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(application).isNotNull();
    }

    @Test
    void buildsRestTemplate() {
        assertThat(restTemplate).isNotNull();
    }

}
