package com.dev.model;

import com.dev.exceptions.InvalidInputException;

import java.math.BigDecimal;

public record PercentageInsure(BigDecimal percentageInsure) {

    public PercentageInsure {
        if (percentageInsure == null || percentageInsure.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException(
                    "Le pourcentage assuré doit être strictement supérieur à 0."
            );
        }
    }
}
