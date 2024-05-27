package com.weave.assignment.backendtakehomehvnuif.controller.request;

import com.weave.assignment.backendtakehomehvnuif.model.Ability;
import com.weave.assignment.backendtakehomehvnuif.model.PokemonType;

import java.util.List;

public class AddPokemonDataRequest {
    String name;
    PokemonType type;
    int height;
    int weight;
    List<Ability> abilities;

    public String getName() {
        return name;
    }

    public PokemonType getType() {
        return type;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }
}
