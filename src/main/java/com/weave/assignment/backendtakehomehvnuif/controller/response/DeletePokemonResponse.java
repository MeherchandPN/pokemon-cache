package com.weave.assignment.backendtakehomehvnuif.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.weave.assignment.backendtakehomehvnuif.model.Ability;
import com.weave.assignment.backendtakehomehvnuif.model.PokemonType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeletePokemonResponse {
    String id;
    String name;
    PokemonType type;
    int height;
    int weight;
    List<Ability> abilities;
    String message;

    public DeletePokemonResponse(String id, String name, PokemonType type, int height, int weight, List<Ability> abilities, String message) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.message = message;
    }

    public DeletePokemonResponse(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

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

    public String getMessage() {
        return message;
    }
}
