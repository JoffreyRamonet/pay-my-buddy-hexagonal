package com.domain.entity;

import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionId;

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
    
    private final TransactionId transactionId;
    private final UUID CONNECTION_ID;
    private final BigDecimal AMOUNT;
    private String description;
    private final LocalDateTime CREATED_AT;
    private final BankAccountId BANK_ACCOUNT_ID;
    
    public Transaction(TransactionId transactionId, UUID CONNECTION_ID, BigDecimal AMOUNT, String description,
                       LocalDateTime CREATED_AT, BankAccountId BANK_ACCOUNT_ID) {
        this.transactionId = transactionId;
        this.CONNECTION_ID = CONNECTION_ID;
        this.AMOUNT = AMOUNT;
        this.description = description;
        this.CREATED_AT = CREATED_AT;
        this.BANK_ACCOUNT_ID = BANK_ACCOUNT_ID;
    }
    
    public Transaction(UUID CONNECTION_ID, BigDecimal AMOUNT, String description, BankAccountId BANK_ACCOUNT_ID) {
        this.transactionId = new TransactionId(UUID.randomUUID());
        this.CONNECTION_ID = CONNECTION_ID;
        this.AMOUNT = AMOUNT;
        this.description = description;
        this.CREATED_AT = LocalDateTime.now();
        this.BANK_ACCOUNT_ID = BANK_ACCOUNT_ID;
    }
    
    public TransactionId getID() {
        return transactionId;
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
    
    public BankAccountId getBANK_ACCOUNT_ID() {
        return BANK_ACCOUNT_ID;
    }
}
