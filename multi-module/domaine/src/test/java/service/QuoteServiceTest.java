package service;

import com.dev.exceptions.InvalidInputException;
import com.dev.model.Quote;
import com.dev.ports.output.ProductRestPort;
import com.dev.ports.output.QuoteRepositoryPort;
import com.dev.service.QuoteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    @Mock
    private QuoteRepositoryPort quoteRepositoryPort;

    @Mock
    private ProductRestPort productRestPort;

    @InjectMocks
    private QuoteService quoteService;

   // traduire les messages en francais

    @Test
    @DisplayName("devrait calculer le capital et enregistrer le devis avec le statut provisoire")
    void should_calculate_capital_and_save_quote_with_provisional_status() {
        // GIVEN
        Quote expectedValue = QuoteTest.setup();

        BigDecimal capitalFromProductService = new BigDecimal(100000);

        // WHEN
        when(quoteRepositoryPort.save(any(Quote.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        when(productRestPort.getCapital(expectedValue.getPercentageInsure().percentageInsure(), expectedValue.getProductType()))
                .thenReturn(capitalFromProductService);

        Quote actualValue = quoteService.createQuote(expectedValue);

        // THEN
        Assertions.assertThat(actualValue)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("ne devrait pas enregistrer le devis lorsque le clientId n'est pas défini")
    void should_not_save_quote_when_client_id_is_not_defined() {
        // GIVEN
        Quote inputQuote = QuoteTest.setup();
        inputQuote.setClientId(null);

        // WHEN & THEN
        Assertions.assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> quoteService.createQuote(inputQuote))
                .withMessage("ClientId ne doit pas être null.");

        verify(quoteRepositoryPort, never()).save(any());
    }

    @Test
    @DisplayName("ne devrait pas enregistrer le devis lorsque le type de produit n'est pas défini")
    void should_not_save_quote_when_product_type_is_not_defined() {
        // GIVEN
        Quote inputQuote = QuoteTest.setup();
        inputQuote.setProductType(null);

        // WHEN & THEN
        Assertions.assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> quoteService.createQuote(inputQuote))
                .withMessage("Type de produit Invalid");

        verify(quoteRepositoryPort, never()).save(any());
    }

}