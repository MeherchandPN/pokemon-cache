package com.weave.assignment.backendtakehomehvnuif.service;

import com.weave.assignment.backendtakehomehvnuif.controller.request.AddPokemonDataRequest;
import com.weave.assignment.backendtakehomehvnuif.controller.response.AddPokemonDataResponse;
import com.weave.assignment.backendtakehomehvnuif.controller.response.DeletePokemonResponse;
import com.weave.assignment.backendtakehomehvnuif.controller.response.GetPokemonDataResponse;
import com.weave.assignment.backendtakehomehvnuif.model.Pokemon;
import com.weave.assignment.backendtakehomehvnuif.service.cache.PokemonCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PokemonService {
    String UNSUCCESSFUL_POKEMON_RETRIEVAL_MESSAGE = "No pokemon present with the given data";
    private final PokemonCache pokemonCache;

    @Autowired
    public PokemonService(PokemonCache pokemonCache) {
        this.pokemonCache = pokemonCache;
    }

    public AddPokemonDataResponse addPokemon(AddPokemonDataRequest request) {
        String id = UUID.randomUUID().toString();
        Pokemon pokemon = new Pokemon(
                id,
                request.getName(),
                request.getType(),
                request.getHeight(),
                request.getWeight(),
                request.getAbilities()
        );
        pokemonCache.set(id, pokemon);
        String SUCCESS_POKEMON_ADDITION_MESSAGE = "Successfully added pokemon";
        return new AddPokemonDataResponse(id, pokemon.name(), SUCCESS_POKEMON_ADDITION_MESSAGE);
    }

    public DeletePokemonResponse deletePokemon(String id) {
        Optional<Pokemon> pokemonOptional = pokemonCache.delete(id);
        if (pokemonOptional.isPresent()) {
            Pokemon pokemon = pokemonOptional.get();
            String SUCCESS_POKEMON_DELETION_MESSAGE = "Successfully deleted pokemon";
            return new DeletePokemonResponse(
                    pokemon.id(),
                    pokemon.name(),
                    pokemon.type(),
                    pokemon.height(),
                    pokemon.weight(),
                    pokemon.abilities(),
                    SUCCESS_POKEMON_DELETION_MESSAGE);
        } else {
            String UNSUCCESSFUL_POKEMON_DELETION_MESSAGE = "Failed to delete pokemon";
            return new DeletePokemonResponse(UNSUCCESSFUL_POKEMON_DELETION_MESSAGE);
        }

    }

    public GetPokemonDataResponse getPokemonById(String id) {
        Optional<Pokemon> pokemonOptional = pokemonCache.get(id);
        if (pokemonOptional.isPresent()) {
            Pokemon pokemon = pokemonOptional.get();
            return new GetPokemonDataResponse(
                    pokemon.id(),
                    pokemon.name(),
                    pokemon.type(),
                    pokemon.height(),
                    pokemon.weight(),
                    pokemon.abilities());
        } else {
            return new GetPokemonDataResponse(UNSUCCESSFUL_POKEMON_RETRIEVAL_MESSAGE);
        }
    }

    public GetPokemonDataResponse getPokemonByName(String name) {
        List<Pokemon> pokemonList = pokemonCache.getAllValues();
        if (!pokemonCache.isEmpty()) {
            Optional<Pokemon> pokemonOptional = pokemonList.stream()
                    .filter(pokemon -> pokemon.name().equalsIgnoreCase(name))
                    .findFirst();
            if (pokemonOptional.isPresent()) {
                Pokemon pokemon = pokemonOptional.get();
                return new GetPokemonDataResponse(
                        pokemon.id(),
                        pokemon.name(),
                        pokemon.type(),
                        pokemon.height(),
                        pokemon.weight(),
                        pokemon.abilities());
            }
        }
        return new GetPokemonDataResponse(UNSUCCESSFUL_POKEMON_RETRIEVAL_MESSAGE);
    }
}
