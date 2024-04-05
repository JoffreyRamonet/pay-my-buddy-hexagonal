package com.domain.api;

import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionDto;
import com.domain.dto.TransactionId;
import com.domain.entity.Transaction;

import java.util.List;

/**
 * Interface to perform requests from controllers to the domain for Transaction entity.
 *
 * @see Transaction
 */
public interface TransactionApi {
    
    List<Transaction> getAll();
    
    Transaction getById(TransactionId transactionId);
    
    Transaction save(TransactionDto transactionDto);
    
    void deleteById(TransactionId transactionId);
    
    Transaction getLastTransactionByBankAccountId(BankAccountId bankAccountId);
    
    void transaction(TransactionDto transactionDto);
}
