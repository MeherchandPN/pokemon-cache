package com.weave.assignment.backendtakehomehvnuif.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddPokemonDataResponse {
    String id;
    String name;
    String message;

    public AddPokemonDataResponse(String id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
