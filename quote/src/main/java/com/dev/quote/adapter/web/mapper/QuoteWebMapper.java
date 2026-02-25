package com.dev.quote.adapter.web.mapper;

import com.dev.quote.adapter.web.dto.QuoteRequest;
import com.dev.quote.adapter.web.dto.QuoteResponse;
import com.dev.quote.domaine.model.CapitalInsure;
import com.dev.quote.domaine.model.ClientId;
import com.dev.quote.domaine.model.PercentageInsure;
import com.dev.quote.domaine.model.Quote;
import org.springframework.stereotype.Component;

@Component
public class QuoteWebMapper {

    public Quote toDomain(QuoteRequest request) {
        return new Quote(new ClientId(request.clientId()),
                request.productType(),
                new PercentageInsure(request.percentageInsure())
        );
    }

    public QuoteResponse toResponse(Quote quote) {
        return new QuoteResponse(
                quote.getId(),
                quote.getClientId().clientId(),
                quote.getStatus(),
                quote.getProductType(),
                quote.getPercentageInsure().percentageInsure(),
                quote.getCapitalInsure(),
                quote.getLifeTime()
        );
    }
}
