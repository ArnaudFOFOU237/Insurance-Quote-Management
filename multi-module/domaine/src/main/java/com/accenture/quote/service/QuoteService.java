package com.accenture.quote.service;

import com.accenture.quote.configurations.DomainService;
import com.accenture.quote.model.valueobject.CapitalInsure;
import com.accenture.quote.model.Quote;
import com.accenture.quote.model.QuoteStatus;
import com.accenture.quote.ports.input.QuoteWebPort;
import com.accenture.quote.ports.output.ProductRestPort;
import com.accenture.quote.ports.output.QuoteRepositoryPort;

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
        quote.setCapital(CapitalInsure.of(capital));
        return quoteRepositoryPort.save(quote);
    }

    @Override
    public Quote updateQuote(UUID id, Quote quote) {
        return  quoteRepositoryPort.update(id, quote);
    }

    @Override
    public Quote updateStatus(UUID id, QuoteStatus newStatus) {
        return quoteRepositoryPort.updateStatus(id, newStatus);
    }

    @Override
    public List<Quote> getQuoteByProductType(String productType) {
        return quoteRepositoryPort.findByProductType(productType);
    }

    @Override
    public List<Quote> getQuoteByClientID(Integer clientId) {
        return quoteRepositoryPort.findByClientId(clientId);
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepositoryPort.findAll();
    }

    private BigDecimal getCapital(Quote quote) {
        return productRestPort.getCapital(quote.getPercentageInsure().percentageInsure(), quote.getProductType());
    }
}
