package com.accenture.quote.model.valueobject;


import com.accenture.quote.exceptions.InvalidInputException;

public record ClientId(Integer clientId) {

    public ClientId {
        if (clientId == null) {
            throw new InvalidInputException("ClientId ne doit pas être null.");
        }
        if (clientId <= 0) {
            throw new InvalidInputException("ClientId ne doit pas être negatif.");
        }
    }

    public static ClientId of(Integer clientId) {
        return new ClientId(clientId);
    }
}