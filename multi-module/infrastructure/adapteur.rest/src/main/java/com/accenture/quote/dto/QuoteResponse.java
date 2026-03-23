package com.accenture.quote.dto;


import com.accenture.quote.model.ProductType;
import com.accenture.quote.model.QuoteStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record QuoteResponse (UUID id, Integer clientID, QuoteStatus status, ProductType productType,
                             BigDecimal percentageInsure, BigDecimal capitalInsure, int lifeTime) {
}
