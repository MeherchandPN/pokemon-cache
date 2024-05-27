package com.weave.assignment.backendtakehomehvnuif.controller;

import com.weave.assignment.backendtakehomehvnuif.controller.request.AddPokemonDataRequest;
import com.weave.assignment.backendtakehomehvnuif.controller.response.AddPokemonDataResponse;
import com.weave.assignment.backendtakehomehvnuif.controller.response.DeletePokemonResponse;
import com.weave.assignment.backendtakehomehvnuif.controller.response.GetPokemonDataResponse;
import com.weave.assignment.backendtakehomehvnuif.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetPokemonDataResponse getPokemonDataById(@PathVariable(required = true) String id) {
        return pokemonService.getPokemonById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetPokemonDataResponse> getPokemonDataByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok().body(pokemonService.getPokemonByName(name));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeletePokemonResponse> deletePokemonDataById(@PathVariable(required = true) String id) {
        return ResponseEntity.ok().body(pokemonService.deletePokemon(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddPokemonDataResponse> addPokemonData(@RequestBody AddPokemonDataRequest request) {
        return ResponseEntity.ok().body(pokemonService.addPokemon(request));
    }
}
