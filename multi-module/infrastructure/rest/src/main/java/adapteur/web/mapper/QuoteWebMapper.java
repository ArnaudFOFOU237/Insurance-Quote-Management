package adapteur.web.mapper;

import adapteur.web.dto.QuoteRequest;
import adapteur.web.dto.QuoteResponse;
import com.dev.model.Quote;
import org.mapstruct.Mapper;

@Mapper
public interface QuoteWebMapper {

     Quote toDomain(QuoteRequest request);

     QuoteResponse toResponse(Quote quote);
}
