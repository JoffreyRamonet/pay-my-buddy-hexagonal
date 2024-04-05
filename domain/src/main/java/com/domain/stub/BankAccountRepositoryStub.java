package com.domain.stub;

import com.domain.ddd.Stub;
import com.domain.dto.BankAccountId;
import com.domain.entity.BankAccount;
import com.domain.spi.BankAccountSpi;

import java.util.List;
import java.util.Optional;

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
    public Optional<BankAccount> findById(BankAccountId bankAccountId) {
        BankAccount bankAccount = bankAccounts.stream()
                .filter(b -> b.getBankAccountId()
                        .bankAccountId()
                        .equals(bankAccountId.bankAccountId()))
                .toList()
                .getFirst();
        return Optional.of(bankAccount);
    }
    
    @Override
    public BankAccount save(BankAccount bankAccount) {
        if(bankAccounts.stream()
                .noneMatch(b -> b.getBankAccountId()
                        .equals(bankAccount.getBankAccountId()))) {
            bankAccounts.add(bankAccount);
        } else {
            for(int i = 0; i <= bankAccounts.size() - 1; i++) {
                if(bankAccounts.get(i)
                        .getBankAccountId()
                        .equals(bankAccount.getBankAccountId())) {
                    bankAccounts.set(i, bankAccount);
                }
            }
        }
        return bankAccount;
    }
    
    @Override
    public void deleteById(BankAccountId bankAccountId) {
        bankAccounts.removeIf(b -> b.getBankAccountId()
                .bankAccountId()
                .equals(bankAccountId.bankAccountId()));
    }
}
