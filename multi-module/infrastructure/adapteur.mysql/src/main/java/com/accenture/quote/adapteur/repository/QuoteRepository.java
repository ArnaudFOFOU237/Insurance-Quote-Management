package com.accenture.quote.adapteur.repository;

import com.accenture.quote.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuoteRepository extends JpaRepository<QuoteEntity, UUID> {

    @Query("SELECT q FROM QuoteEntity q WHERE q.clientId = :clientId")
    List<QuoteEntity> findByClientId(int clientId);

    @Query("SELECT q FROM QuoteEntity q WHERE q.productType = :productType")
    List<QuoteEntity> findByProductType(String productType);
}
