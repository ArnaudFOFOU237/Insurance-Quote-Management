package adapteurs.mappers;

import adapteurs.entity.QuoteEntity;
import com.dev.model.CapitalInsure;
import com.dev.model.ClientId;
import com.dev.model.PercentageInsure;
import com.dev.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface QuotePersistanceMapper {

    QuotePersistanceMapper INSTANCE = Mappers.getMapper( QuotePersistanceMapper.class );

    QuoteEntity toEntity(Quote quote);

    Quote toDomaine(QuoteEntity quoteEntity);
}