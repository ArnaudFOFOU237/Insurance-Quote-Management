package com.accenture.quote.model;

import com.accenture.quote.exceptions.InvalidInputException;
import com.accenture.quote.model.valueobject.PercentageInsure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("PercentageInsure Value Object")
class PercentageInsureTest {

    @Test
    @DisplayName("devrait creer un PercentageInsure avec une valeur valide")
    void should_create_with_valid_percentage() {
        PercentageInsure pct = PercentageInsure.of(new BigDecimal("75.50"));

        assertThat(pct.percentageInsure())
                .isEqualByComparingTo("75.50");
    }

    @ParameterizedTest(name = "valid percentage = {0}")
    @ValueSource(strings = {"0", "0.01", "50", "99.99", "100"})
    @DisplayName("devrait accepter les valeurs limites")
    void should_accept_boundary_values(String value) {
        assertThatNoException()
                .isThrownBy(() -> PercentageInsure.of(PercentageInsure.of(new BigDecimal(value)).percentageInsure()));
    }

    @ParameterizedTest(name = "invalid percentage = {0}")
    @ValueSource(strings = {"-0.01", "-1", "100.01", "200"})
    @DisplayName("devrait lancer une exception pour les valeurs hors limites")
    void should_throw_for_out_of_range_values(String value) {
        assertThatThrownBy(() -> PercentageInsure.of(PercentageInsure.of(new BigDecimal(value)).percentageInsure()))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("Le pourcentage assuré doit être compris entre 0 et 100.");
    }

    @Test
    @DisplayName("devrait lancer une exception pour une valeur null")
    void should_throw_when_null() {
        assertThatThrownBy(() -> PercentageInsure.of(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("Le pourcentage assuré doit être null.");
    }
}