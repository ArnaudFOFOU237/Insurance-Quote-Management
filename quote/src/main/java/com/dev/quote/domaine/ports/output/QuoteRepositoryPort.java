package com.dev.quote.domaine.ports.output;

import com.dev.quote.domaine.model.Quote;

import java.util.List;
import java.util.UUID;

public interface QuoteRepositoryPort {
    Quote save(Quote quote);
    Quote update(UUID id, Quote quote);
    Quote findById(UUID id);
    List<Quote> findAll();
}
