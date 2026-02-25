package com.dev.quote.service;

import com.dev.quote.config.Configurations;
import com.dev.quote.domaine.exception.InvalidInputException;
import com.dev.quote.domaine.exception.ServiceUnavailableException;
import com.dev.quote.domaine.model.ProductType;
import com.dev.quote.domaine.model.Quote;
import com.dev.quote.domaine.model.QuoteStatus;
import com.dev.quote.domaine.ports.output.QuoteRepositoryPort;
import com.dev.quote.domaine.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    @Mock
    private QuoteRepositoryPort quoteRepositoryPort;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    void should_calculate_capital_and_save_quote_with_provisional_status() throws ServiceUnavailableException {
        // GIVEN
        Quote inputQuote = QuoteTest.setup();

        BigDecimal expectedCapital = new BigDecimal(15000);

        when(quoteRepositoryPort.save(any(Quote.class))).thenAnswer(i -> i.getArguments()[0]);

        // WHEN
        Quote result = quoteService.createQuote(inputQuote);

        // THEN
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(inputQuote.getClientId(), result.getClientId());
        assertEquals(inputQuote.getPercentageInsure(), result.getPercentageInsure());
        assertEquals(inputQuote.getProductType(), result.getProductType());
        assertEquals(QuoteStatus.PROVISOIRE, result.getStatus());
    }

    @Test
    void should_not_save_quote_when_client_id_is_missing() {
        // GIVEN
        Quote inputQuote = QuoteTest.setup();
        inputQuote.setClientId(null);

        // WHEN & THEN
        assertThrows(InvalidInputException.class, () -> {
            quoteService.createQuote(inputQuote);
        });

        verify(quoteRepositoryPort, never()).save(any());
    }

    @Test
    void should_not_save_quote_when_product_type_is_missing() {
        // GIVEN
        Quote inputQuote = QuoteTest.setup();
        inputQuote.setProductType(null);

        // WHEN & THEN
        assertThrows(InvalidInputException.class, () -> {
            quoteService.createQuote(inputQuote);
        });

        verify(quoteRepositoryPort, never()).save(any());
    }

}
