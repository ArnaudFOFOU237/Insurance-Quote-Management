package com.dev.quote.adapter.persistance.mappers;

import com.dev.quote.adapter.persistance.entity.QuoteEntity;
import com.dev.quote.domaine.model.CapitalInsure;
import com.dev.quote.domaine.model.ClientId;
import com.dev.quote.domaine.model.PercentageInsure;
import com.dev.quote.domaine.model.Quote;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class QuotePersistanceMapper {

    public QuoteEntity toEntity(Quote quote) {
        return new QuoteEntity(
                quote.getId(),
                quote.getClientId().clientId(),
                quote.getStatus(),
                quote.getProductType(),
                quote.getPercentageInsure().percentageInsure(),
                quote.getCapitalInsure().capital(),
                quote.getLifeTime()
        );
    }

    public Quote toDomaine(QuoteEntity quoteEntity) {
        return new Quote(
                quoteEntity.getId(),
                new ClientId(quoteEntity.getClientId()),
                quoteEntity.getStatus(),
                quoteEntity.getProductType(),
                new PercentageInsure(quoteEntity.getPercentageInsure()),
                new CapitalInsure(quoteEntity.getCapitalInsure()),
                quoteEntity.getLifeTime()
        );
    }
}
