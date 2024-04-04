package com.domain.spi;

import com.domain.dto.TransactionDto;
import com.domain.entity.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for Transaction entity.
 *
 * @see Transaction
 */
public interface TransactionSpi {
    
    List<Transaction> findAll();
    Optional<Transaction> findById(UUID id);
    Transaction save(TransactionDto transactionDto);
    void deleteById(UUID id);
    Transaction findTheLastTransactionByBankAccountId(UUID id);
    
}
