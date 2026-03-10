package adapteurs.rest.dto;

import com.dev.model.ProductType;
import jakarta.validation.constraints.Min;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;

public record QuoteRequest (@NonNull ProductType productType, @NonNull Integer clientId, @Min(value = 0) BigDecimal percentageInsure){
}
