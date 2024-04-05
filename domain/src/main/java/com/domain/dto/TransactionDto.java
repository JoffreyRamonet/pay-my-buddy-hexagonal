package com.domain.dto;

import java.math.BigDecimal;

/**
 * Object to get the data input from the transaction form.
 * Used to parse data to perform and create a new transaction.
 * Used to parse data to update BankAccount's balance attribute.
 */
public record TransactionDto(Email connection, BigDecimal amount, String description, BankAccountId bankAccountId) {
}
