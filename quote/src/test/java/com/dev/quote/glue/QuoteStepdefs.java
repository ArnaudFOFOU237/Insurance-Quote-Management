package com.dev.quote.glue;

import com.dev.quote.domaine.model.Quote;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.web.servlet.client.assertj.RestTestClientResponse;

import java.util.List;


public class QuoteStepdefs {

    private final String URL = "/api/v1/quotes";
    @Autowired
    private RestTestClient restTestClient;
    private RestTestClientResponse response;
    private Quote quote;

    @Given("The Insure give the followings information:")
    public void theInsureGiveTheFollowingsInformation(List<Quote> quote) {
        this.quote = quote.getFirst();
    }

    @When("The Insure save the quote with those information")
    public void theInsureSaveTheQuoteWithThoseInformation() {
        this.response = RestTestClientResponse.from (this.restTestClient.post().uri(URL)
                .accept(MediaType.APPLICATION_JSON)
                .body(this.quote)
                .exchange());
    }

    @Then("The quote is save")
    public void theQuoteIsSave() {
        Assertions.assertNotNull(this.response.getExchangeResult().getResponseBodyContent());
    }

    @And("the status code is created")
    public void theStatusCodeIsCreated() {
        this.response.getExchangeResult().getStatus().is2xxSuccessful();
    }
}
