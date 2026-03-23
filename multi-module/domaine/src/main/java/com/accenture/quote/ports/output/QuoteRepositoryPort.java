package com.accenture.quote.ports.output;


import com.accenture.quote.model.Quote;
import com.accenture.quote.model.QuoteStatus;

import java.util.List;
import java.util.UUID;

public interface QuoteRepositoryPort {
    Quote save(Quote quote);
    Quote update(UUID id, Quote quote);
    Quote updateStatus(UUID id, QuoteStatus quoteStatus);
    List<Quote> findAll();
    List<Quote> findByClientId(Integer clientId);
    List<Quote> findByProductType(String productType);
}
