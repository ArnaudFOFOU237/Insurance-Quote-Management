package adapteurs.rest.controllers;

import adapteurs.rest.dto.QuoteRequest;
import adapteurs.rest.dto.QuoteResponse;
import adapteurs.rest.mapper.QuoteWebMapper;
import com.dev.model.Quote;
import com.dev.ports.input.QuoteWebPort;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
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

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuoteResponse> createQuote(@Valid @RequestBody QuoteRequest quoteRequest) {
        Quote quoteToCreate = quoteMapper.toDomain(quoteRequest);
        Quote savedQuote = quoteWebPort.createQuote(quoteToCreate);
        return ResponseEntity.created(URI.create("/api/v1/quotes")).body(quoteMapper.toResponse(savedQuote));
    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<QuoteResponse> getQuote(@RequestParam(name = "quoteId") UUID quoteId){
        Quote quote = quoteWebPort.getQuoteByID(quoteId);
        return ResponseEntity.ok().body(quoteMapper.toResponse(quote));
    }

    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAllQuote() {
        List<Quote> quotes = quoteWebPort.getAllQuotes();
        return ResponseEntity.ok().body(quotes.stream().map(quoteMapper::toResponse).toList());
    }
}