package com.dev.model;


import com.dev.exceptions.ServiceUnavailableException;

import java.math.BigDecimal;

public record CapitalInsure(BigDecimal capital) {

    public CapitalInsure {
        if (capital == null)
            throw new ServiceUnavailableException("Le service produit est indisponible. Veuillez réessayer plus tard.");
    }
}
