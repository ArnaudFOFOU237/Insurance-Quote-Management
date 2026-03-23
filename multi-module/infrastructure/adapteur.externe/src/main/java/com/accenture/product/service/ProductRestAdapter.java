package com.accenture.product.service;

import com.accenture.quote.model.ProductType;
import com.accenture.quote.ports.output.ProductRestPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Component
public class ProductRestAdapter implements ProductRestPort {

    private final RestClient restClient;

    private final String PRODUCT_API = "/api/v1/produits";

    public ProductRestAdapter(RestClient.Builder builder, @Value("${external.api.url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    @Override
    public BigDecimal getCapital(BigDecimal percentage, ProductType productType) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(PRODUCT_API)
                        .queryParam("percentage", percentage)
                        .queryParam("type", productType)
                        .build())
                .retrieve()
                .body(BigDecimal.class);
    }
    private void getDefaultProduct(Throwable throwable) {
       throw new RuntimeException("Service de calcul de capital indisponible");
    }

}
