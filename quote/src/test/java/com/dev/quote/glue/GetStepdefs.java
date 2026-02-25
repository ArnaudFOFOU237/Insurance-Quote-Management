package com.dev.quote.glue;

import com.dev.quote.domaine.model.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.web.servlet.client.assertj.RestTestClientResponse;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetStepdefs {

    private final String URL = "/api/v1/quotes";
    @Autowired
    private RestTestClient restTestClient;
    private RestTestClientResponse response;
    private Quote quote;

    @Given("A quote exists in the system with ID {string}")
    public void aQuoteExistsInTheSystemWithID(String id) {
        this.quote = new Quote(UUID.fromString(id),
                new ClientId(2534),
                QuoteStatus.PROVISOIRE,
                ProductType.AUTO,
                new PercentageInsure(new BigDecimal(50)),
                new CapitalInsure(new BigDecimal(750000)),
                24);
    }

    @When("I request the quote with ID {string}")
    public void iRequestTheQuoteWithID(String quoteId) {
        this.response = RestTestClientResponse.from(
                this.restTestClient.get()
                        .uri(URL + "/" + quoteId)
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange()
        );
    }

    @Then("The response status should be {int}")
    public void theResponseStatusShouldBe(int arg0) {
        this.response.getExchangeResult().getStatus().is2xxSuccessful();
    }

    @And("The product type should be {string}")
    public void theProductTypeShouldBe(String expectedProductType) {
      //.  assertEquals(actualQuote.getProductType(), ProductType.valueOf(expectedProductType));
    }

    @And("The client ID should be {int}")
    public void theClientIDShouldBe(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
