package adapteur.web.dto;


import com.dev.model.CapitalInsure;
import com.dev.model.ProductType;
import com.dev.model.QuoteStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record QuoteResponse (UUID id, Integer clientID, QuoteStatus status, ProductType productType,
                             BigDecimal percentageInsure, CapitalInsure capitalInsure, int lifeTime) {
}
