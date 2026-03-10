package com.dev.usecase;


import com.dev.model.Quote;
import com.dev.model.QuoteStatus;

import java.util.UUID;

public interface UpdateUseCase {
    Quote updateQuote(UUID id, Quote request);
    Quote updateStatus(UUID id, QuoteStatus newStatus);
}
