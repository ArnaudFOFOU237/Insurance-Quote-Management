package com.accenture.quote.usecase;

import com.accenture.quote.model.ProductType;
import com.accenture.quote.model.Quote;

import java.util.List;

public interface GetUseCase {
    List<Quote> getQuoteByProductType(String productType);
    List<Quote> getQuoteByClientID(Integer clientId);
    List<Quote> getAllQuotes();

}
