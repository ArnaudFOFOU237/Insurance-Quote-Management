package com.dev.quote.domaine.usecase;

import com.dev.quote.domaine.model.ProductType;
import com.dev.quote.domaine.model.Quote;

import java.util.List;
import java.util.UUID;

public interface GetUseCase {
    Quote getQuoteByProductType(ProductType productType);
    Quote getQuoteByClientID(int clientId);
    Quote getQuoteByID(UUID quoteId);
    List<Quote> getAllQuotes();

}
