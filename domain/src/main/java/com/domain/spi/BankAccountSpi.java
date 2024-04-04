package com.domain.spi;

import com.domain.entity.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for BankAccount entity.
 *
 * @see BankAccount
 */
public interface BankAccountSpi {
    
    List<BankAccount> findAll();
    Optional<BankAccount> findById(UUID id);
    BankAccount save(BankAccount bankAccount);
    void deleteById(UUID id);
    
}