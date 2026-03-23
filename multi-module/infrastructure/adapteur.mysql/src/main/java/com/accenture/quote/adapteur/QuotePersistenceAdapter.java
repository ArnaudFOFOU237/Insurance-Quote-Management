package com.accenture.quote.adapteur;


import com.accenture.quote.adapteur.repository.QuoteRepository;
import com.accenture.quote.entity.QuoteEntity;
import com.accenture.quote.exceptions.BadRequestException;
import com.accenture.quote.exceptions.ResourceNotFoundException;
import com.accenture.quote.mappers.QuotePersistenceMapper;
import com.accenture.quote.model.Quote;
import com.accenture.quote.model.QuoteStatus;
import com.accenture.quote.ports.output.QuoteRepositoryPort;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class QuotePersistenceAdapter implements QuoteRepositoryPort {

    private final String CACHE_NAME = "quotesCache";
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

    @CachePut(value = CACHE_NAME)
    @Override
    public Quote update(UUID id, Quote quote) {
        if (id == null || quote == null || quote.getId() == null) {
            throw new BadRequestException("Mauvaise requête: id et quote.id doivent être renseignés.");
        }

        QuoteEntity quoteEntity = this.getQuoteById(id);
        if(quoteEntity != null && !id.equals(quoteEntity.getId())) {
            throw new BadRequestException("Mauvaise requête: l'objet ne correspond pas à l'Id fourni.");
        }

        if (!id.equals(quote.getId())) {
            throw new BadRequestException("Mauvaise requête: l'objet ne correspond pas à l'Id fourni.");
        }

        QuoteEntity toSave = quotePersistenceMapper.toEntity(quote);
        QuoteEntity saved = quoteRepository.save(toSave);
        return quotePersistenceMapper.toDomain(saved);
    }

    @Override
    public Quote updateStatus(UUID id, QuoteStatus quoteStatus) {
        if (id == null || quoteStatus == null) {
            throw new BadRequestException("Mauvaise requête: id et quoteStatus doivent être renseignés.");
        }
        QuoteEntity quoteEntity = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found for id: " + id));
        quoteEntity.setStatus(quoteStatus);
        QuoteEntity saved = quoteRepository.save(quoteEntity);
        return quotePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Quote> findAll() {
        List<QuoteEntity> quoteEntities = quoteRepository.findAll();
        return quoteEntities.stream().map(quotePersistenceMapper::toDomain).toList();
    }

    @Cacheable(value = CACHE_NAME, key = "#clientId")
    @Override
    public List<Quote> findByClientId(Integer clientId) {
        List<QuoteEntity> quoteEntities = getQuoteByClientId(clientId);
        if (quoteEntities == null) {
            throw new ResourceNotFoundException("Quote non trouvée pour le client Id: " + clientId);
        }
        return quoteEntities.stream().map(quotePersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Quote> findByProductType(String productType) {
        List<QuoteEntity> quoteEntities = quoteRepository.findByProductType(productType);
        if (quoteEntities == null) {
            throw new ResourceNotFoundException("Quote non trouvée pour le type de produit: " + productType);
        }
        return quoteEntities.stream().map(quotePersistenceMapper::toDomain).toList();
    }

    private QuoteEntity getQuoteById(UUID id) {
        return quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote non trouvée"));
    }

    private List<QuoteEntity> getQuoteByClientId(Integer clientId) {
        return quoteRepository.findByClientId(clientId);
    }
}
