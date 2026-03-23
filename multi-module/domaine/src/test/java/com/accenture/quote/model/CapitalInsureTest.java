package com.accenture.quote.model;

import com.accenture.quote.exceptions.InvalidInputException;
import com.accenture.quote.exceptions.ServiceUnavailableException;
import com.accenture.quote.model.valueobject.CapitalInsure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@DisplayName("CapitalInsure Value Object")
class CapitalInsureTest {

    @Test
    @DisplayName("devrait creer un CapitalInsure avec une valeur positive")
    void should_create_withPositiveValue() {
        CapitalInsure capital = CapitalInsure.of(new BigDecimal("50000.00"));

        assertThat(capital.capital())
                .isEqualByComparingTo("50000.00");
    }

    @Test
    @DisplayName("devrait generer une exception lorsque la valeur est nulle")
    void should_throw_whenNull() {
        assertThatThrownBy(() -> CapitalInsure.of(null))
                .isInstanceOf(ServiceUnavailableException.class)
                .hasMessageContaining("Le service produit est indisponible. Veuillez réessayer plus tard.");
    }

    @Test
    @DisplayName("devrait generer une exception lorsque la valeur est zero")
    void should_throw_when_zero() {
        assertThatThrownBy(() -> CapitalInsure.of(BigDecimal.ZERO))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("Le capital assuré doit être strictement positif.");
    }

    @Test
    @DisplayName("devrait generer une exception lorsque la valeur est negative")
    void should_throw_when_negative() {
        assertThatThrownBy(() -> CapitalInsure.of(new BigDecimal("-1000.00")))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("Le capital assuré doit être strictement positif.");
    }

    @Test
    @DisplayName("devrait accepter la valeur minimale positive")
    void should_accept_minimum_boundary() {
        assertThatNoException()
                .isThrownBy(() -> CapitalInsure.of(new BigDecimal("0.01")));
    }
}