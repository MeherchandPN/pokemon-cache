package com.weave.assignment.backendtakehomehvnuif.model;

import java.util.HashMap;
import java.util.Map;

public enum PokemonType {
    FIRE("FIRE"),
    GRASS("GRASS"),
    WATER("WATER"),
    PSYCHIC("PSYCHIC"),
    ELECTRIC("ELECTRIC"),
    DRAGON("DRAGON"),
    FLYING("FLYING");

    private final String pokemonTypeString;

    PokemonType(String pokemonTypeStringString) {
        this.pokemonTypeString = pokemonTypeStringString;
    }

    public String getPokemonTypeString() {
        return pokemonTypeString;
    }

    private static final Map<String, PokemonType> lookUp = new HashMap<>();

    static {
        for (PokemonType type : PokemonType.values()) {
            lookUp.put(type.pokemonTypeString, type);
        }
    }

    public static PokemonType get(String pokemonTypeString) {
        return lookUp.get(pokemonTypeString);
    }
}
