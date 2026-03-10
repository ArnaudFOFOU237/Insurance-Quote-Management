package com.dev.ports.output;


import com.dev.model.ProductType;

import java.math.BigDecimal;

public interface ProductRestPort {

    BigDecimal getCapital(BigDecimal percentage, ProductType productType);
}
