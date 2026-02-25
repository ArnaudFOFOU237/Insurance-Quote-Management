package com.dev.quote.domaine.service;

import com.dev.quote.annotation.DomainService;
import com.dev.quote.domaine.model.*;
import com.dev.quote.domaine.ports.input.QuoteWebPort;
import com.dev.quote.domaine.ports.output.ProductRestPort;
import com.dev.quote.domaine.ports.output.QuoteRepositoryPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@DomainService
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
        return productRestPort.getComputeCapital(quote.getPercentageInsure().percentageInsure(), quote.getProductType());
    }
}
