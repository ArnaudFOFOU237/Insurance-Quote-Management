package com.dev.quote.domaine.ports.output;

import com.dev.quote.domaine.model.ProductType;

import java.math.BigDecimal;

public interface ProductRestPort {

    BigDecimal getComputeCapital(BigDecimal percentage, ProductType productType);
}
