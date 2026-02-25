package com.dev.quote.domaine.model;

import com.dev.quote.domaine.exception.ServiceUnavailableException;

import java.math.BigDecimal;

public record CapitalInsure(BigDecimal capital) {

    public CapitalInsure {
        if (capital == null)
            throw new ServiceUnavailableException("Le service produit est indisponible. Veuillez réessayer plus tard.");
    }
}
