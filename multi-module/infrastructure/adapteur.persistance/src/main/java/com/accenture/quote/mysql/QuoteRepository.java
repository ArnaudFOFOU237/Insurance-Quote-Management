package com.accenture.quote.mysql;

import com.accenture.quote.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuoteRepository extends JpaRepository<QuoteEntity, UUID> {

    List<QuoteEntity> findByClientId(int clientId);
}
