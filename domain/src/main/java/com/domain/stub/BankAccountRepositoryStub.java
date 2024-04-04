package com.domain.stub;

import com.domain.ddd.Stub;
import com.domain.entity.BankAccount;
import com.domain.spi.BankAccountSpi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * A fake class to perform BankAccountSpi request.
 * <p>
 * Use the @Stub annotation to create a Bean for the framework used in the infrastructure layer for integration test.
 *
 * @see Stub
 */
@Stub
public class BankAccountRepositoryStub implements BankAccountSpi {
    
    private final DataBaseStub data = new DataBaseStub();
    private final List<BankAccount> bankAccounts = data.bankAccounts();
    
    @Override
    public List<BankAccount> findAll() {
        return bankAccounts;
    }
    
    @Override
    public Optional<BankAccount> findById(UUID id) {
        BankAccount bankAccount = bankAccounts.stream()
                .filter(b -> b.getID()
                        .equals(id))
                .toList()
                .getFirst();
        return Optional.of(bankAccount);
    }
    
    @Override
    public BankAccount save(BankAccount bankAccount) {
        if(bankAccounts.stream()
                .noneMatch(b -> b.getID()
                        .equals(bankAccount.getID()))) {
            bankAccounts.add(bankAccount);
        } else {
            for(int i = 0; i <= bankAccounts.size(); i++) {
                if(bankAccounts.get(i)
                        .getID()
                        .equals(bankAccount.getID())) {
                    bankAccounts.set(i, bankAccount);
                }
            }
        }
        return bankAccount;
    }
    
    @Override
    public void deleteById(UUID id) {
        bankAccounts.removeIf(b -> b.getID()
                .equals(id));
    }
}
