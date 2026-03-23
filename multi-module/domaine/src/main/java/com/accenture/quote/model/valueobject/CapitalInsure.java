package com.accenture.quote.model.valueobject;


import com.accenture.quote.exceptions.InvalidInputException;
import com.accenture.quote.exceptions.ServiceUnavailableException;

import java.math.BigDecimal;

public record CapitalInsure(BigDecimal capital) {
    public CapitalInsure {
        if (capital == null)
            throw new ServiceUnavailableException("Le service produit est indisponible. Veuillez réessayer plus tard.");
        if (capital.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidInputException("Le capital assuré doit être strictement positif.");
    }

    public static CapitalInsure of(BigDecimal value) {
        return new CapitalInsure(value);
    }
}
