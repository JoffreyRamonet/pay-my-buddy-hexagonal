package com.domain.entity;

import com.domain.dto.BankAccountId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * BankAccount entity.
 * Used to store a balance with two internationals bank account identifier.
 * It's generate at the user registration.
 * id, iban and swift are three random UUID, generated at the bankAccount creation.
 * transactions is a list of all transactions linked at this bankAccount.
 */
public class BankAccount {
    
    private final BankAccountId bankAccountId;
    private BigDecimal balance;
    private final UUID IBAN;
    private final UUID SWIFT;
    private List<UUID> transactions;
    
    public BankAccount(BankAccountId ID, BigDecimal balance, UUID IBAN, UUID SWIFT, List<UUID> transactions) {
        this.bankAccountId = ID;
        this.balance = balance;
        this.IBAN = IBAN;
        this.SWIFT = SWIFT;
        this.transactions = transactions;
    }
    
    public BankAccount() {
        this.bankAccountId = new BankAccountId(UUID.randomUUID());
        this.balance = new BigDecimal("0.00");
        this.IBAN = UUID.randomUUID();
        this.SWIFT = UUID.randomUUID();
        this.transactions = new ArrayList<>();
    }
    
    
    public BankAccountId getBankAccountId() {
        return bankAccountId;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public UUID getIBAN() {
        return IBAN;
    }
    
    public UUID getSWIFT() {
        return SWIFT;
    }
    
    public List<UUID> getTransactions() {
        return transactions;
    }
    
    public void setTransactions(List<UUID> transactions) {
        this.transactions = transactions;
    }
}
