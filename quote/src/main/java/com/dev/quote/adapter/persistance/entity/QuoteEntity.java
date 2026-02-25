package com.dev.quote.adapter.persistance.entity;

import com.dev.quote.domaine.model.ProductType;
import com.dev.quote.domaine.model.QuoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Quote")
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private Integer clientId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuoteStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @Column(nullable = false)
    private BigDecimal percentageInsure;

    @Column(nullable = false)
    private BigDecimal capitalInsure;

    @Column(nullable = false)
    private int lifeTime;
}