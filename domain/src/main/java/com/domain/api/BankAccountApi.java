package com.domain.api;

import com.domain.dto.BankAccountDto;
import com.domain.dto.TransactionDto;
import com.domain.entity.BankAccount;

import java.util.List;
import java.util.UUID;

/**
 * Interface to perform requests from controllers to the domain for BankAccount entity.
 *
 * @see BankAccount
 */
public interface BankAccountApi {
    
    List<BankAccount> getAll();
    BankAccount getById(UUID id);
    BankAccount save(BankAccount bankAccount);
    void deleteById(UUID id);
    void transaction(TransactionDto transactionDto);
    void deposit(BankAccountDto bankAccountDto);
    void external(BankAccountDto bankAccountDto);
    Boolean ableToDeposit(TransactionDto transactionDto);
    
}
