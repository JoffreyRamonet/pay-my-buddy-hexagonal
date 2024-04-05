package com.domain.api;

import com.domain.dto.BankAccountDto;
import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionDto;
import com.domain.entity.BankAccount;

import java.util.List;

/**
 * Interface to perform requests from controllers to the domain for BankAccount entity.
 *
 * @see BankAccount
 */
public interface BankAccountApi {
    
    List<BankAccount> getAll();
    
    BankAccount getById(BankAccountId bankAccountId);
    
    BankAccount save(BankAccount bankAccount);
    
    void deleteById(BankAccountId bankAccountId);
    
    void deposit(BankAccountDto bankAccountDto);
    
    void external(BankAccountDto bankAccountDto);
    
    Boolean ableToPay(TransactionDto transactionDto);
}
