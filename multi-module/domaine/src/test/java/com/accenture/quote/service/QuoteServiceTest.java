package com.accenture.quote.service;

import com.accenture.quote.exceptions.InvalidInputException;
import com.accenture.quote.exceptions.ResourceNotFoundException;
import com.accenture.quote.model.valueobject.CapitalInsure;
import com.accenture.quote.model.ProductType;
import com.accenture.quote.model.Quote;
import com.accenture.quote.model.QuoteStatus;
import com.accenture.quote.ports.output.ProductRestPort;
import com.accenture.quote.ports.output.QuoteRepositoryPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    @Mock
    private QuoteRepositoryPort quoteRepositoryPort;

    @Mock
    private ProductRestPort productRestPort;

    @InjectMocks
    private QuoteService quoteService;

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
        expectedValue.setCapital(new CapitalInsure(capitalFromProductService));
        expectedValue.setLifeTime(24);
        Assertions.assertThat(actualValue)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedValue);
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

    @Test
    @DisplayName("devrait mettre à jour le devis avec les nouvelles informations")
    void should_update_quote() {
        // GIVEN
        Quote expectedValue = QuoteTest.setupWithID();
        expectedValue.setProductType(ProductType.SANTE);
        // WHEN
        when(quoteRepositoryPort.update(any(), any(Quote.class)))
                .thenAnswer(i -> i.getArguments()[1]);

        Quote actualValue = quoteService.updateQuote(expectedValue.getId(), expectedValue);

        // THEN
        Assertions.assertThat(actualValue)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("devrait mettre à jour le statut du devis")
    void should_update_quote_status() {
        // GIVEN
        Quote expectedValue = QuoteTest.setupWithID();
        QuoteStatus newStatus = QuoteStatus.ACCEPTE;
        // WHEN
        Quote updatedQuote = new Quote(expectedValue.getId(), expectedValue.getClientId(), newStatus, expectedValue.getProductType(), expectedValue.getPercentageInsure(), expectedValue.getCapitalInsure(), expectedValue.getLifeTime());
        when(quoteRepositoryPort.updateStatus(any(), eq(newStatus)))
                .thenReturn(updatedQuote);

        Quote actualValue = quoteService.updateStatus(expectedValue.getId(), newStatus);

        // THEN
        Assertions.assertThat(actualValue).isNotNull();
        Assertions.assertThat(actualValue.getStatus()).isEqualTo(newStatus);

    }

    @Test
    @DisplayName("devrait retourner la liste des devis correspondant au type de produit")
    void should_return_quote_by_product_type() {
        // GIVEN
        Quote expectedValue = QuoteTest.setupWithID();
        String productType = expectedValue.getProductType().name();
        // WHEN
        when(quoteRepositoryPort.findByProductType(productType))
                .thenReturn(java.util.List.of(expectedValue));
        // THEN
        Assertions.assertThat(quoteService.getQuoteByProductType(productType))
                .isNotNull()
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expectedValue);
    }

    @Test
    @DisplayName("devrait retourner la liste des devis correspondant à l'identifiant du client")
    void should_return_quote_by_client_id() {
        // GIVEN
        Quote expectedValue = QuoteTest.setupWithID();
        Integer clientId = expectedValue.getClientId().clientId();
        // WHEN
        when(quoteRepositoryPort.findByClientId(clientId))
                .thenReturn(java.util.List.of(expectedValue));
        // THEN
        Assertions.assertThat(quoteService.getQuoteByClientID(clientId))
                .isNotNull()
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expectedValue);
    }

    @Test
    @DisplayName("devrait retourner la liste de tous les devis")
    void should_return_all_quote() {
        // GIVEN
        Quote expectedValue = QuoteTest.setupWithID();
        // WHEN
        when(quoteRepositoryPort.findAll())
                .thenReturn(java.util.List.of(expectedValue));
        // THEN
        Assertions.assertThat(quoteService.getAllQuotes())
                .isNotNull()
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expectedValue);
    }

    @Test
    @DisplayName("devrait lancer une exception lorsque le devis n'est pas trouvé pour l'identifiant du client")
    void should_throw_exception_when_quote_is_not_found_with_client_id() {
        // Given
        int clientId = 123;

        when(quoteRepositoryPort.findByClientId(clientId))
                .thenThrow(new ResourceNotFoundException("Quote non trouver pour le client Id: " + clientId));;
        // When / Then
        Assertions.assertThatThrownBy(() -> quoteService.getQuoteByClientID(clientId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Quote non trouver pour le client Id: " + clientId);
    }

    @Test
    @DisplayName("devrait lancer une exception lorsque le devis n'est pas trouvé pour le type de produit")
    void should_throw_exception_when_quote_is_not_found_with_product_type() {
        // Given
        String productType = "UNKNOWN_TYPE";
        when(quoteRepositoryPort.findByProductType(productType))
                .thenThrow(new ResourceNotFoundException("Quote non trouvée pour le type de produit: " + productType));

        // When / Then
        Assertions.assertThatThrownBy(() -> quoteService.getQuoteByProductType(productType))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Quote non trouvée pour le type de produit: " + productType);
    }
}