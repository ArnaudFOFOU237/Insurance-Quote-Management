package com.dev.model;

import com.dev.exceptions.InvalidInputException;

import java.util.UUID;

public class Quote {

    private UUID id;
    private ClientId clientId;
    private QuoteStatus status;
    private ProductType productType;
    private PercentageInsure percentageInsure;
    private CapitalInsure capitalInsure;
    private int lifeTime;

    public Quote() {}

    public Quote(ClientId clientId, ProductType productType, PercentageInsure percentageInsure) {
        this.clientId = clientId;
        this.productType = productType;
        this.percentageInsure = percentageInsure;
        this.status = QuoteStatus.PROVISOIRE;
    }

    public Quote( UUID id, ClientId clientId, QuoteStatus status, ProductType productType,
                  PercentageInsure percentageInsure, CapitalInsure capitalInsure, int lifeTime) {
        this.id = id;
        this.clientId = clientId;
        this.status = status;
        this.productType = productType;
        this.percentageInsure = percentageInsure;
        this.capitalInsure = capitalInsure;
        this.lifeTime = lifeTime;
    }


    public ClientId getClientId() {
        return clientId;
    }

    public void setClientId(ClientId clientId) {
        this.clientId = clientId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public QuoteStatus getStatus() {
        return status;
    }

    public void setStatus(QuoteStatus status) {
        this.status = status;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public PercentageInsure getPercentageInsure() {
        return percentageInsure;
    }

    public void setPercentageInsure(PercentageInsure percentageInsure) {
        this.percentageInsure = percentageInsure;
    }

    public CapitalInsure getCapitalInsure() {
        return capitalInsure;
    }

    public void setCapitalInsure(CapitalInsure capitalInsure) {
        this.capitalInsure = capitalInsure;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void setCapital(CapitalInsure capitalInsure) {
        this.setCapitalInsure(capitalInsure);
        this.setStatus(QuoteStatus.PROVISOIRE);
        this.setLifeTime(24);
    }

    public void validate() {
        if (this.clientId == null) {
            throw new InvalidInputException("ClientId ne doit pas être null.");
        }
        if (this.productType == null) {
            throw new InvalidInputException("Type de produit Invalid");
        }

        boolean isValidProductType = ProductType.isValid((this.getProductType().name()));
        if (!isValidProductType) {
            throw new InvalidInputException("Type de produit Invalid");
        }
    }
}
