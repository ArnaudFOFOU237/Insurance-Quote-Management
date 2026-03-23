package com.accenture.quote.controllers;

import com.accenture.quote.dto.QuoteRequest;
import com.accenture.quote.dto.QuoteResponse;
import com.accenture.quote.mapper.QuoteWebMapper;
import com.accenture.quote.model.Quote;
import com.accenture.quote.model.QuoteStatus;
import com.accenture.quote.ports.input.QuoteWebPort;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteWebPort quoteWebPort;
    private final QuoteWebMapper quoteMapper;

    public QuoteController(QuoteWebPort quoteWebPort, QuoteWebMapper quoteMapper) {
        this.quoteWebPort = quoteWebPort;
        this.quoteMapper = quoteMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, version = "1.0")
    public ResponseEntity<QuoteResponse> createQuote(@Valid @RequestBody QuoteRequest quoteRequest) {
        Quote quoteToCreate = quoteMapper.toDomain(quoteRequest);
        Quote savedQuote = quoteWebPort.createQuote(quoteToCreate);
        return ResponseEntity.created(URI.create("/api/v1/quotes")).body(quoteMapper.toResponse(savedQuote));
    }

    @GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE,version = "1.0")
    public ResponseEntity<List<QuoteResponse>> getQuoteByClientId(@PathVariable(name = "clientId") Integer  clientId){
        log.info("Fetching quotes for clientId: {}", clientId);
        List<Quote> quotes = quoteWebPort.getQuoteByClientID(clientId);
        List<QuoteResponse> quoteResponses = quotes.stream().map(quoteMapper::toResponse).toList();
        return ResponseEntity.ok().body(quoteResponses);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, version = "1.0")
    public ResponseEntity<List<QuoteResponse>> getAllQuote() {
        List<Quote> quotes = quoteWebPort.getAllQuotes();
        return ResponseEntity.ok().body(quotes.stream().map(quoteMapper::toResponse).toList());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, version = "1.0")
    public ResponseEntity<QuoteResponse> updateQuote(@PathVariable(name = "id") UUID id, @Valid @RequestBody QuoteRequest quoteRequest) {
        Quote quoteToUpdate = quoteMapper.toDomain(quoteRequest);
        quoteToUpdate.setId(quoteRequest.id());
        Quote updatedQuote = quoteWebPort.updateQuote(id, quoteToUpdate);
        return ResponseEntity.ok().body(quoteMapper.toResponse(updatedQuote));
    }

     @PatchMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE, version = "1.0")
     public ResponseEntity<QuoteResponse> updateStatus(@PathVariable(name = "id") UUID id, @RequestParam QuoteStatus newStatus) {
        Quote updatedQuote = quoteWebPort.updateStatus(id, newStatus);
        return ResponseEntity.ok().body(quoteMapper.toResponse(updatedQuote));
    }
}