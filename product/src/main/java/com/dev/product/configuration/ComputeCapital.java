package com.dev.product.configuration;

import com.dev.product.model.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComputeCapital {

    public static BigDecimal calculateCapital(BigDecimal percentage, ProductType type) throws Exception {
        if (percentage == null || type == null) {
            throw new Exception("Le pourcentage et le type de produit sont obligatoires.");
        }

        if (percentage.compareTo(BigDecimal.ZERO) <= 0 || percentage.compareTo(new BigDecimal("100")) > 0) {
            throw new Exception("Le pourcentage doit être dans l'intervalle (0, 100].");
        }

        BigDecimal base = switch (type) {
            case AUTO       -> new BigDecimal("15000");
            case HABITATION -> new BigDecimal("200000");
            case VIE        -> new BigDecimal("500000");
            default         -> new BigDecimal("50000");
        };

        return base
                .multiply(percentage)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

}
