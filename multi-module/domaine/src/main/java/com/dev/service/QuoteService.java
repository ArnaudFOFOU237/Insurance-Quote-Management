package com.dev.service;

import com.dev.model.CapitalInsure;
import com.dev.model.ProductType;
import com.dev.model.Quote;
import com.dev.model.QuoteStatus;
import com.dev.ports.input.QuoteWebPort;
import com.dev.ports.output.ProductRestPort;
import com.dev.ports.output.QuoteRepositoryPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class QuoteService implements QuoteWebPort {

    private final QuoteRepositoryPort quoteRepositoryPort;
    private final ProductRestPort productRestPort;

    public QuoteService(QuoteRepositoryPort quoteRepositoryPort, ProductRestPort productRestPort) {
        this.quoteRepositoryPort = quoteRepositoryPort;
        this.productRestPort = productRestPort;
    }

    @Override
    public Quote createQuote(Quote quote) {
        quote.validate();
        BigDecimal capital = getCapital(quote);
        quote.setCapital(new CapitalInsure(capital));
        return quoteRepositoryPort.save(quote);
    }

    @Override
    public Quote updateQuote(UUID id, Quote quote) {
        return  quoteRepositoryPort.update(id, quote);
    }

    @Override
    public Quote updateStatus(UUID id, QuoteStatus newStatus) {
        return null;
    }

    @Override
    public Quote getQuoteByProductType(ProductType productType) {
        return null;
    }

    @Override
    public Quote getQuoteByClientID(int clientId) {
        return null;
    }

    @Override
    public Quote getQuoteByID(UUID quoteId) {
        return quoteRepositoryPort.findById(quoteId);
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepositoryPort.findAll();
    }

    private BigDecimal getCapital(Quote quote) {
        return productRestPort.getCapital(quote.getPercentageInsure().percentageInsure(), quote.getProductType());
    }
}
