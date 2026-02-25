package com.dev.quote.domaine.model;

import com.dev.quote.domaine.exception.InvalidInputException;

public record ClientId(Integer clientId) {

    public ClientId {
        if (clientId == null) {
            throw new InvalidInputException("ClientId ne doit pas être nul.");
        }
    }
}
// canonique et compact constructor