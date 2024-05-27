package com.weave.assignment.backendtakehomehvnuif.service.cache;

import com.weave.assignment.backendtakehomehvnuif.model.Ability;
import com.weave.assignment.backendtakehomehvnuif.model.Pokemon;
import com.weave.assignment.backendtakehomehvnuif.model.PokemonType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PokemonCacheTest {

    @Test
    public void addSomeDataToCache_WhenGetData_ThenIsEqualWithCacheElement() {
        PokemonCache pokemonCache = new PokemonCache();
        Pokemon pokemon1 = new Pokemon("1", "test1", PokemonType.GRASS, 10, 10, List.of(new Ability("leaf attack")));
        pokemonCache.set("1", pokemon1);
        assertEquals("test1", pokemonCache.get("1").get().name());

        Pokemon pokemon2 = new Pokemon("2", "test2", PokemonType.GRASS, 10, 10, List.of(new Ability("leaf attack")));
        pokemonCache.set("2", pokemon2);
        assertEquals("test2", pokemonCache.get("2").get().name());

        Pokemon pokemon3 = new Pokemon("3", "test3", PokemonType.GRASS, 10, 10, List.of(new Ability("leaf attack")));
        pokemonCache.set("3", pokemon3);
        assertEquals("test3", pokemonCache.get("3").get().name());
    }

    @Test
    public void addDataToCacheToTheNumberOfSize_WhenAddOneMoreData_ThenLeastRecentlyDataWillEvict() {
        PokemonCache pokemonCache = new PokemonCache();
        Pokemon pokemon1 = new Pokemon("1", "test1", PokemonType.GRASS, 10, 10, List.of(new Ability("leaf attack")));
        pokemonCache.set("1", pokemon1);

        Pokemon pokemon2 = new Pokemon("2", "test2", PokemonType.GRASS, 10, 10, List.of(new Ability("leaf attack")));
        pokemonCache.set("2", pokemon2);

        Pokemon pokemon3 = new Pokemon("3", "test3", PokemonType.GRASS, 10, 10, List.of(new Ability("leaf attack")));
        pokemonCache.set("3", pokemon3);

        Pokemon pokemon4 = new Pokemon("4", "test4", PokemonType.GRASS, 10, 10, List.of(new Ability("leaf attack")));
        pokemonCache.set("4", pokemon4);
        assertFalse(pokemonCache.get("1").isPresent());
    }
}