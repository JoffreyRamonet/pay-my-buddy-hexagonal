package com.domain.dto;

import java.util.UUID;

/**
 * Contains the UUID of a Transaction object.
 *
 * @param transactionId the id
 * @see com.domain.entity.Transaction
 */
public record TransactionId(UUID transactionId) {
}
