package adapteur.web.controllers;

import com.dev.model.ProductType;
import com.dev.ports.output.ProductRestPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Slf4j
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
        log.info("Appel de la methode fallback");
       throw new RuntimeException("Service de calcul de capital indisponible");
    }

}
