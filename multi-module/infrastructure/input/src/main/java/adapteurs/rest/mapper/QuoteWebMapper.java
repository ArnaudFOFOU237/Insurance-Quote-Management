package adapteurs.rest.mapper;

import adapteurs.rest.dto.QuoteRequest;
import adapteurs.rest.dto.QuoteResponse;
import com.dev.model.ClientId;
import com.dev.model.PercentageInsure;
import com.dev.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public interface QuoteWebMapper {

    QuoteWebMapper INSTANCE = Mappers.getMapper( QuoteWebMapper.class );

    default ClientId map(Integer clienId) {
        return new ClientId(clienId);
    }

    default Integer map(ClientId clientId) {
        return clientId.clientId();
    }

    default PercentageInsure map(BigDecimal percentage) {
         return new PercentageInsure(percentage);
    }

    default BigDecimal map(PercentageInsure percentageInsure) {
        return percentageInsure.percentageInsure();
    }

     Quote toDomain(QuoteRequest request);

     QuoteResponse toResponse(Quote quote);
}
