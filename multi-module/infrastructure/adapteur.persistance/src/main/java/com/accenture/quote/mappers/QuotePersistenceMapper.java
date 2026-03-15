package com.accenture.quote.mappers;

import com.accenture.quote.entity.QuoteEntity;
import com.dev.model.CapitalInsure;
import com.dev.model.ClientId;
import com.dev.model.PercentageInsure;
import com.dev.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface QuotePersistenceMapper {

    @Mapping(target = "clientId",         source = "clientId",         qualifiedByName = "toClientId")
    @Mapping(target = "percentageInsure", source = "percentageInsure", qualifiedByName = "toPercentageInsure")
    @Mapping(target = "capitalInsure",    source = "capitalInsure",    qualifiedByName = "toCapitalInsure")
    Quote toDomain(QuoteEntity entity);

    @Mapping(target = "clientId",         source = "clientId",         qualifiedByName = "fromClientId")
    @Mapping(target = "percentageInsure", source = "percentageInsure", qualifiedByName = "fromPercentageInsure")
    @Mapping(target = "capitalInsure",    source = "capitalInsure",    qualifiedByName = "fromCapitalInsure")
    QuoteEntity toEntity(Quote quote);

    @Named("toClientId")
    default ClientId toClientId(Integer clientId) {
        return new ClientId(clientId);
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