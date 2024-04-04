package com.domain.api;

import com.domain.dto.TransactionDto;
import com.domain.entity.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * Interface to perform requests from controllers to the domain for Transaction entity.
 *
 * @see Transaction
 */
public interface TransactionApi {
    
    List<Transaction> getAll();
    Transaction getById(UUID uuid);
    Transaction save(TransactionDto transactionDto);
    void deleteById(UUID id);
    Transaction getLastTransactionByBankAccountId(UUID id);
    
}
