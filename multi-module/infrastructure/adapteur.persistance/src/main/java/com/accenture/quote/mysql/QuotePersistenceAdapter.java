package com.accenture.quote.mysql;


import com.accenture.quote.entity.QuoteEntity;
import com.accenture.quote.mappers.QuotePersistenceMapper;
import com.dev.exceptions.BadRequestException;
import com.dev.exceptions.ResourceNotFoundException;
import com.dev.model.Quote;
import com.dev.ports.output.QuoteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class QuotePersistenceAdapter implements QuoteRepositoryPort {

    private final QuoteRepository quoteRepository;
    private final QuotePersistenceMapper quotePersistenceMapper;

    public QuotePersistenceAdapter(QuoteRepository quoteRepository, QuotePersistenceMapper quotePersistenceMapper) {
        this.quoteRepository = quoteRepository;
        this.quotePersistenceMapper = quotePersistenceMapper;
    }

    @Override
    public Quote save(Quote quote) {
        QuoteEntity quoteEntity = quotePersistenceMapper.toEntity(quote);
        QuoteEntity quoteSave = quoteRepository.save(quoteEntity);
        return quotePersistenceMapper.toDomain(quoteSave);
    }

    @Override
    public Quote update(UUID id, Quote quote) {
        if (id == null || quote == null || quote.getId() == null) {
            throw new BadRequestException("Mauvaise requête: id et quote.id doivent être renseignés.");
        }
        if (!id.equals(quote.getId())) {
            throw new BadRequestException("Mauvaise requête: l'objet ne correspond pas à l'Id fourni.");
        }

        this.getQuoteById(id);
        QuoteEntity toSave = quotePersistenceMapper.toEntity(quote);
        QuoteEntity saved = quoteRepository.save(toSave);
        return quotePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Quote> findAll() {
        List<QuoteEntity> quoteEntities = quoteRepository.findAll();
        return quoteEntities.stream().map(quotePersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Quote> findByClientId(Integer clientId) {
        List<QuoteEntity> quoteEntities = getQuoteByClientId(clientId);
        if (quoteEntities == null) {
            throw new ResourceNotFoundException("Quote not found for clientId: " + clientId);
        }
        return quoteEntities.stream().map(quotePersistenceMapper::toDomain).toList();
    }

    private void getQuoteById(UUID id) {
        quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));
    }

    private List<QuoteEntity> getQuoteByClientId(Integer clientId) {
        return quoteRepository.findByClientId(clientId);
    }
}
