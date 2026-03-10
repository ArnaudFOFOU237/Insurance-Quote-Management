package com.dev.model;


import com.dev.exceptions.InvalidInputException;

public record ClientId(Integer clientId) {

    public ClientId {
        if (clientId == null) {
            throw new InvalidInputException("ClientId ne doit pas être null.");
        }
    }
}