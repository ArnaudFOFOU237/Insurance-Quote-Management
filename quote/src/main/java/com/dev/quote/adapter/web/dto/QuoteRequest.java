package com.dev.quote.adapter.web.dto;

import com.dev.quote.domaine.model.ProductType;
import jakarta.validation.constraints.Min;
import lombok.NonNull;

import java.math.BigDecimal;

public record QuoteRequest (@NonNull ProductType productType, @NonNull Integer clientId, @Min(value = 0) BigDecimal percentageInsure){
}
