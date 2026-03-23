package com.accenture.quote.dto;

import com.accenture.quote.model.ProductType;
import jakarta.validation.constraints.Min;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

public record QuoteRequest (UUID id, @NonNull ProductType productType, @NonNull Integer clientId, @Min(value = 0) BigDecimal percentageInsure){
}
