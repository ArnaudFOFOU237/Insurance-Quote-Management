package com.dev.quote.domaine.ports.input;

import com.dev.quote.domaine.usecase.CreateUseCase;
import com.dev.quote.domaine.usecase.GetUseCase;
import com.dev.quote.domaine.usecase.UpdateUseCase;

public interface QuoteWebPort extends CreateUseCase, GetUseCase, UpdateUseCase {
}
