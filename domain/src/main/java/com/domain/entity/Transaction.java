package com.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Transaction model.
 * Every money transfer between two Users generate a transaction object.
 * transaction number is a random UUID generated at the transaction creation.
 * connection_id is the User's id who while be credited.
 * amount is the amount of the money movement.
 * description is a note for the user who send money.
 * createdAt is the local date time of the transaction creation, generated at the creation.
 * bank_account_id is the BankAccount's id of the debited user.
 */
public class Transaction {
    
    private final UUID ID;
    private final UUID CONNECTION_ID;
    private final BigDecimal AMOUNT;
    private String description;
    private final LocalDateTime CREATED_AT;
    private final UUID BANK_ACCOUNT_ID;
    
    public Transaction(UUID ID, UUID CONNECTION_ID, BigDecimal AMOUNT, String description, LocalDateTime CREATED_AT,
                       UUID BANK_ACCOUNT_ID) {
        this.ID = ID;
        this.CONNECTION_ID = CONNECTION_ID;
        this.AMOUNT = AMOUNT;
        this.description = description;
        this.CREATED_AT = CREATED_AT;
        this.BANK_ACCOUNT_ID = BANK_ACCOUNT_ID;
    }
    
    public Transaction(UUID CONNECTION_ID, BigDecimal AMOUNT, String description, UUID BANK_ACCOUNT_ID) {
        this.ID = UUID.randomUUID();
        this.CONNECTION_ID = CONNECTION_ID;
        this.AMOUNT = AMOUNT;
        this.description = description;
        this.CREATED_AT = LocalDateTime.now();
        this.BANK_ACCOUNT_ID = BANK_ACCOUNT_ID;
    }
    
    public UUID getID() {
        return ID;
    }
    
    public UUID getCONNECTION_ID() {
        return CONNECTION_ID;
    }
    
    public BigDecimal getAMOUNT() {
        return AMOUNT;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getCREATED_AT() {
        return CREATED_AT;
    }
    
    public UUID getBANK_ACCOUNT_ID() {
        return BANK_ACCOUNT_ID;
    }
}
