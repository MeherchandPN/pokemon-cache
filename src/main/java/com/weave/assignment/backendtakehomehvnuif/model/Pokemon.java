package com.weave.assignment.backendtakehomehvnuif.model;

import java.util.List;

public record Pokemon(String id, String name, PokemonType type, int height, int weight, List<Ability> abilities) {
}
