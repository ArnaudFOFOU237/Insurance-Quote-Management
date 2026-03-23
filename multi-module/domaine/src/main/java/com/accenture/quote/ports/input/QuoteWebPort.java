package com.accenture.quote.ports.input;


import com.accenture.quote.usecase.CreateUseCase;
import com.accenture.quote.usecase.GetUseCase;
import com.accenture.quote.usecase.UpdateUseCase;

public interface QuoteWebPort extends CreateUseCase, GetUseCase, UpdateUseCase {
}
