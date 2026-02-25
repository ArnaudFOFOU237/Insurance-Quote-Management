package com.dev.quote.domaine.usecase;

import com.dev.quote.domaine.exception.ServiceUnavailableException;
import com.dev.quote.domaine.model.Quote;

public interface CreateUseCase {
    Quote createQuote(Quote quote) throws ServiceUnavailableException;
}
