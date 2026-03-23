package com.accenture.quote.usecase;


import com.accenture.quote.model.Quote;
import com.accenture.quote.model.QuoteStatus;

import java.util.UUID;

public interface UpdateUseCase {
    Quote updateQuote(UUID id, Quote request);
    Quote updateStatus(UUID id, QuoteStatus newStatus);
}
