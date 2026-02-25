package com.dev.quote.domaine.usecase;

import com.dev.quote.domaine.model.Quote;
import com.dev.quote.domaine.model.QuoteStatus;

import java.util.UUID;

public interface UpdateUseCase {
    Quote updateQuote(UUID id, Quote request);
    Quote updateStatus(UUID id, QuoteStatus newStatus);
}
