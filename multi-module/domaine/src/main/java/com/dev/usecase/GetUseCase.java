package com.dev.usecase;

import com.dev.model.ProductType;
import com.dev.model.Quote;

import java.util.List;
import java.util.UUID;

public interface GetUseCase {
    Quote getQuoteByProductType(ProductType productType);
    Quote getQuoteByClientID(int clientId);
    Quote getQuoteByID(UUID quoteId);
    List<Quote> getAllQuotes();

}
