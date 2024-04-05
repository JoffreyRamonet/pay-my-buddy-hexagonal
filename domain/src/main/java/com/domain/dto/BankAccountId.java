package com.domain.dto;

import java.util.UUID;

/**
 * Contains the UUID of a BankAccount object.
 *
 * @param bankAccountId the id
 * @see com.domain.entity.BankAccount
 */
public record BankAccountId(UUID bankAccountId) {
}
