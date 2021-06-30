package com.truelayer.pokedex.downstreamapi.client;

public interface PokedexClient<T, K> {
    K call(T input);
}
