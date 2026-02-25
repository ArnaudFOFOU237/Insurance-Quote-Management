package com.dev.quote.repository;

import com.dev.quote.adapter.persistance.entity.QuoteEntity;
import com.dev.quote.adapter.persistance.mysql.QuoteRepository;
import com.dev.quote.domaine.model.ProductType;
import com.dev.quote.domaine.model.QuoteStatus;
import com.dev.quote.repository.config.MySQLDBTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;


@DataJpaTest
@Import(MySQLDBTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuoteRepositoryIntegrationTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @BeforeEach
    void setup() {
        quoteRepository.deleteAll();
    }

    @Test
    public void shouldSaveMovieInfoSuccessfully(){
        //Given
        QuoteEntity quoteEntity = new QuoteEntity(null,234, QuoteStatus.PROVISOIRE, ProductType.VIE, new BigDecimal(80), new BigDecimal(45000), 48);
        //When
        QuoteEntity quoteEntitySave = quoteRepository.save(quoteEntity);
        //Then
        Assertions.assertNotNull(quoteEntitySave.getId());
    }
}
