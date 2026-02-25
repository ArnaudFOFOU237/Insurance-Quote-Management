package com.dev.quote.domaine.model;

import java.util.Arrays;

public enum ProductType {
    AUTO, SANTE, HABITATION, VIE;

    public static boolean isValid(String value) {
        return Arrays.stream(ProductType.values())
                .anyMatch(status -> status.name().equalsIgnoreCase(value));
    }
}
