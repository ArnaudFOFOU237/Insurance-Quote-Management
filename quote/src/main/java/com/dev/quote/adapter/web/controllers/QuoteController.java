package com.dev.quote.adapter.web.controllers;

import com.dev.quote.adapter.web.dto.QuoteRequest;
import com.dev.quote.adapter.web.dto.QuoteResponse;
import com.dev.quote.adapter.web.mapper.QuoteWebMapper;
import com.dev.quote.domaine.exception.ServiceUnavailableException;
import com.dev.quote.domaine.model.Quote;
import com.dev.quote.domaine.ports.input.QuoteWebPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {

    private final QuoteWebPort quoteWebPort;
    private final QuoteWebMapper quoteMapper;

    public QuoteController(QuoteWebPort quoteWebPort, QuoteWebMapper quoteMapper) {
        this.quoteWebPort = quoteWebPort;
        this.quoteMapper = quoteMapper;
    }

    @PostMapping
    public ResponseEntity<QuoteResponse> createQuote(@Valid @RequestBody QuoteRequest quoteRequest) throws ServiceUnavailableException {
        Quote quoteToCreate = quoteMapper.toDomain(quoteRequest);
        Quote savedQuote = quoteWebPort.createQuote(quoteToCreate);
        return ResponseEntity.created(URI.create("/api/v1/quotes")).body(quoteMapper.toResponse(savedQuote));
    }

    @GetMapping("{quoteId}")
    public ResponseEntity<QuoteResponse> getQuote(@RequestParam(name = "quoteId") UUID quoteId){
        Quote quote = quoteWebPort.getQuoteByID(quoteId);
        return ResponseEntity.ok().body(quoteMapper.toResponse(quote));
    }

    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAllQuote(){
        List<Quote> quotes = quoteWebPort.getAllQuotes();
        return ResponseEntity.ok().body(quotes.stream().map(quoteMapper::toResponse).toList());
    }
}
