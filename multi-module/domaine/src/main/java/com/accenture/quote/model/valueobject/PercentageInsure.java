package com.accenture.quote.model.valueobject;

import com.accenture.quote.exceptions.InvalidInputException;

import java.math.BigDecimal;

public record PercentageInsure(BigDecimal percentageInsure) {

    public PercentageInsure {
        if (percentageInsure == null )
            throw new InvalidInputException("Le pourcentage assuré doit être null.");

        if (percentageInsure.compareTo(BigDecimal.ZERO) < 0 || percentageInsure.compareTo(new BigDecimal("100")) > 0)
            throw new InvalidInputException("Le pourcentage assuré doit être compris entre 0 et 100.");
    }

    public static PercentageInsure of(BigDecimal value) {
        return new PercentageInsure(value);
    }
}
