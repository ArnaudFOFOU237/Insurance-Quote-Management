package com.dev.ports.output;


import com.dev.model.Quote;

import java.util.List;
import java.util.UUID;

public interface QuoteRepositoryPort {
    Quote save(Quote quote);
    Quote update(UUID id, Quote quote);
    List<Quote> findAll();
    List<Quote> findByClientId(Integer clientId);
}
