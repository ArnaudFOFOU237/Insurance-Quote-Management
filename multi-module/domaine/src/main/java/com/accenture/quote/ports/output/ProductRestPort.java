package com.accenture.quote.ports.output;


import com.accenture.quote.model.ProductType;

import java.math.BigDecimal;

public interface ProductRestPort {

    BigDecimal getCapital(BigDecimal percentage, ProductType productType);
}
