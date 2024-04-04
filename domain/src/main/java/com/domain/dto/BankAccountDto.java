package com.domain.dto;

import java.math.BigDecimal;

/**
 * Object to get the amount value from the transfer form.
 * Used to parse the amount from an external deposit to a local BankAccount.
 * Used to parse the amount from the local BankAccount to an external account.
 */
public record BankAccountDto(BigDecimal amount) {
}
