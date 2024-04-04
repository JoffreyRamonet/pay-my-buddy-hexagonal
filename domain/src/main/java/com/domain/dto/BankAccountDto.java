package com.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Object to get the amount value from the transfer form.
 * Used to parse the amount from an external deposit to a local BankAccount.
 * Used to parse the amount from the local BankAccount to an external account.
 */
public record BankAccountDto(UUID id, BigDecimal amount) {
}
