package adapteurs.mappers;

import adapteurs.entity.QuoteEntity;
import com.dev.model.CapitalInsure;
import com.dev.model.ClientId;
import com.dev.model.PercentageInsure;
import com.dev.model.Quote;
import org.springframework.stereotype.Component;

@Component
public class QuotePersistanceMapper {

    public QuoteEntity toEntity(Quote quote) {
        return new QuoteEntity(
                quote.getId(),
                quote.getClientId().clientId(),
                quote.getStatus(),
                quote.getProductType(),
                quote.getPercentageInsure().percentageInsure(),
                quote.getCapitalInsure().capital(),
                quote.getLifeTime()
        );
    }

    public Quote toDomaine(QuoteEntity quoteEntity) {
        return new Quote(
                quoteEntity.getId(),
                new ClientId(quoteEntity.getClientId()),
                quoteEntity.getStatus(),
                quoteEntity.getProductType(),
                new PercentageInsure(quoteEntity.getPercentageInsure()),
                new CapitalInsure(quoteEntity.getCapitalInsure()),
                quoteEntity.getLifeTime()
        );
    }
}
