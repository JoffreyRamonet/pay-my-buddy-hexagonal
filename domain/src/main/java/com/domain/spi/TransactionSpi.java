package com.domain.spi;

import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionId;
import com.domain.entity.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for Transaction entity.
 *
 * @see Transaction
 */
public interface TransactionSpi {
    
    List<Transaction> findAll();
    
    Optional<Transaction> findById(TransactionId transactionId);
    
    Transaction save(Transaction transaction);
    
    void deleteById(TransactionId transactionId);
    
    Transaction findTheLastTransactionByBankAccountId(BankAccountId bankAccountId);
}
