package com.accenture.quote.model;

import com.accenture.quote.exceptions.InvalidInputException;
import com.accenture.quote.model.valueobject.ClientId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ClientIdTest {

    @Test
    @DisplayName("devrait creer un ClientId avec une valeur valide")
    void should_create_with_validId() {

        int clientIdValue = 123;
        ClientId clientId = ClientId.of(clientIdValue);

        assertThat(clientId.clientId())
                .isEqualTo(clientIdValue);
    }

    @Test
    @DisplayName("devrait lancer une exception pour une valeur null")
    void should_throw_when_null_client_id() {
        assertThatThrownBy(() -> ClientId.of(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("ClientId ne doit pas être null.");
    }

    @Test
    @DisplayName("devrait lancer une exception pour une valeur negatif")
    void should_throw_when_negative_client_id() {
        assertThatThrownBy(() -> ClientId.of(-123))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("ClientId ne doit pas être negatif.");
    }
}