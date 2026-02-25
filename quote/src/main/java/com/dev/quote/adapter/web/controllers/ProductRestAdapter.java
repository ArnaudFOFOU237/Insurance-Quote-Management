package com.dev.quote.adapter.web.controllers;

import com.dev.quote.domaine.model.ProductType;
import com.dev.quote.domaine.ports.output.ProductRestPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Slf4j
@Component
public class ProductRestAdapter implements ProductRestPort {

    private final RestClient restClient;

    public ProductRestAdapter(RestClient.Builder builder, @Value("${external.api.url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    @Override
    @CircuitBreaker(name = "productCircuitBreaker", fallbackMethod = "getDefaultProduct")
    public BigDecimal getComputeCapital(BigDecimal percentage, ProductType productType) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/produits")
                        .queryParam("percentage", percentage)
                        .queryParam("type", productType)
                        .build())
                .retrieve()
                .body(BigDecimal.class);
    }
    BigDecimal getDefaultProduct(Throwable throwable){
        log.info("Appel de la methode fallback");
        return BigDecimal.ZERO;
    }
}
