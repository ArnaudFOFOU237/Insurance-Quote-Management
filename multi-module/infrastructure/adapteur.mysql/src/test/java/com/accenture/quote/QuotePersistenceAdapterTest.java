package com.accenture.quote;

import com.accenture.quote.adapteur.repository.QuoteRepository;
import com.accenture.quote.config.MySQLDBTestConfig;
import com.accenture.quote.entity.QuoteEntity;
import com.accenture.quote.model.ProductType;
import com.accenture.quote.model.QuoteStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(MySQLDBTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QuotePersistenceAdapterTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @BeforeEach
    void setup() {
        quoteRepository.deleteAll();
    }

    @Test
    @DisplayName("Should save QuoteEntity successfully")
    void should_save_quoteEntity_successfully() {
        // Given
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setClientId(123);
        quoteEntity.setProductType(ProductType.SANTE);
        quoteEntity.setStatus(QuoteStatus.PROVISOIRE);

        // When
        QuoteEntity savedQuote = quoteRepository.save(quoteEntity);

        // Then
        assertNotNull(savedQuote.getId());
        assertEquals(123, savedQuote.getClientId());
        assertEquals(ProductType.SANTE, savedQuote.getProductType());
        assertEquals(QuoteStatus.PROVISOIRE, savedQuote.getStatus());
    }

    @Test
    @DisplayName("Should update QuoteEntity successfully")
    void should_update_quoteEntity_successfully() {
        // Given
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setClientId(123);
        quoteEntity.setProductType(ProductType.SANTE);
        quoteEntity.setStatus(QuoteStatus.PROVISOIRE);
        QuoteEntity savedQuote = quoteRepository.save(quoteEntity);

        // When
        savedQuote.setStatus(QuoteStatus.REFUSE);
        QuoteEntity updatedQuote = quoteRepository.save(savedQuote);

        // Then
        assertEquals(QuoteStatus.REFUSE, updatedQuote.getStatus());
    }

    @Test
    @DisplayName("Should update Quote status successfully")
    void should_updateStatus_successfully() {
        // Given
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setClientId(123);
        quoteEntity.setProductType(ProductType.SANTE);
        quoteEntity.setStatus(QuoteStatus.PROVISOIRE);
        QuoteEntity savedQuote = quoteRepository.save(quoteEntity);

        // When
        savedQuote.setStatus(QuoteStatus.ACCEPTE);
        QuoteEntity updatedQuote = quoteRepository.save(savedQuote);

        // Then
        assertEquals(QuoteStatus.ACCEPTE, updatedQuote.getStatus());
    }

    @Test
    @DisplayName("Should find all QuoteEntity successfully")
    void should_findAll_successfully() {
        // Given
        QuoteEntity quote1 = new QuoteEntity();
        quote1.setClientId(123);
        quote1.setProductType(ProductType.SANTE);
        quote1.setStatus(QuoteStatus.PROVISOIRE);
        QuoteEntity quote2 = new QuoteEntity();
        quote2.setClientId(456);
        quote2.setProductType(ProductType.VIE);
        quote2.setStatus(QuoteStatus.ACCEPTE);
        quoteRepository.save(quote1);
        quoteRepository.save(quote2);

        // When
        var quotes = quoteRepository.findAll();

        // Then
        assertEquals(2, quotes.size());
    }

    @Test
    @DisplayName("Should find QuoteEntity by client Id successfully")
    void should_findByClientId_successfully() {
        // Given
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setClientId(123);
        quoteEntity.setProductType(ProductType.SANTE);
        quoteEntity.setStatus(QuoteStatus.PROVISOIRE);
        quoteRepository.save(quoteEntity);

        // When
        var quotes = quoteRepository.findByClientId(123);

        // Then
        assertEquals(1, quotes.size());
        assertEquals(123, quotes.get(0).getClientId());
    }

    @Test
    @DisplayName("Should find QuoteEntity by product type successfully")
    void should_findByProductType_successfully() {
        // Given
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setClientId(123);
        quoteEntity.setProductType(ProductType.SANTE);
        quoteEntity.setStatus(QuoteStatus.PROVISOIRE);
        quoteRepository.save(quoteEntity);

        // When
        var quotes = quoteRepository.findByProductType("SANTE");

        // Then
        assertEquals(1, quotes.size());
        assertEquals(ProductType.SANTE, quotes.get(0).getProductType());
    }
}