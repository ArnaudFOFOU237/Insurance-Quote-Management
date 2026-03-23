package com.accenture.quote.mapper;

import com.accenture.quote.dto.QuoteRequest;
import com.accenture.quote.dto.QuoteResponse;
import com.accenture.quote.model.valueobject.CapitalInsure;
import com.accenture.quote.model.valueobject.ClientId;
import com.accenture.quote.model.valueobject.PercentageInsure;
import com.accenture.quote.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface QuoteWebMapper {


    @Mapping(target = "clientId",         source = "clientId",         qualifiedByName = "toClientId")
    @Mapping(target = "percentageInsure", source = "percentageInsure", qualifiedByName = "toPercentageInsure")
    Quote toDomain(QuoteRequest request);

    @Mapping(target = "clientID",         source = "clientId",         qualifiedByName = "fromClientId")
    @Mapping(target = "percentageInsure", source = "percentageInsure", qualifiedByName = "fromPercentageInsure")
    @Mapping(target = "capitalInsure",    source = "capitalInsure", qualifiedByName= "fromCapitalInsure")
    QuoteResponse toResponse(Quote quote);

    @Named("toClientId")
    default ClientId toClientId(Integer value) {
        return new ClientId(value);
    }

    @Named("fromClientId")
    default Integer fromClientId(ClientId clientId) {
        return clientId.clientId();
    }

    @Named("toPercentageInsure")
    default PercentageInsure toPercentageInsure(BigDecimal value) {
        return new PercentageInsure(value);
    }

    @Named("fromPercentageInsure")
    default BigDecimal fromPercentageInsure(PercentageInsure percentageInsure) {
        return percentageInsure.percentageInsure();
    }

    @Named("toCapitalInsure")
    default CapitalInsure toCapitalInsure(BigDecimal value) {
        return new CapitalInsure(value);
    }

    @Named("fromCapitalInsure")
    default BigDecimal fromCapitalInsure(CapitalInsure capitalInsure) {
        return capitalInsure.capital();
    }
}
