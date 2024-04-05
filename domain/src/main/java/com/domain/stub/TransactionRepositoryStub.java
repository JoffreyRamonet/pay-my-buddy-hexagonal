package com.domain.stub;

import com.domain.ddd.Stub;
import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionId;
import com.domain.entity.Transaction;
import com.domain.spi.TransactionSpi;

import java.util.List;
import java.util.Optional;

/**
 * A fake class to perform TransactionSpi request.
 * <p>
 * Use the @Stub annotation to create a Bean for the framework used in the infrastructure layer for integration test.
 *
 * @see Stub
 */
@Stub
public class TransactionRepositoryStub implements TransactionSpi {
    
    private final DataBaseStub data = new DataBaseStub();
    private final List<Transaction> transactions = data.transactions();
    
    @Override
    public List<Transaction> findAll() {
        return transactions;
    }
    
    @Override
    public Optional<Transaction> findById(TransactionId transactionId) {
        Transaction transaction = transactions.stream()
                .filter(t -> t.getID()
                        .transactionId()
                        .equals(transactionId.transactionId()))
                .toList()
                .getFirst();
        return Optional.of(transaction);
    }
    
    @Override
    public Transaction save(Transaction transaction) {
        if(transactions.stream()
                .noneMatch(t -> t.getID()
                        .equals(transaction.getID()))) {
            transactions.add(transaction);
        } else {
            for(int i = 0; i <= transactions.size(); i++) {
                if(transactions.get(i)
                        .getID()
                        .equals(transaction.getID())) {
                    transactions.set(i, transaction);
                }
            }
        }
        
        return null;
    }
    
    @Override
    public void deleteById(TransactionId transactionId) {
        transactions.removeIf(t -> t.getID()
                .transactionId()
                .equals(transactionId.transactionId()));
    }
    
    @Override
    public Transaction findTheLastTransactionByBankAccountId(BankAccountId bankAccountId) {
        return transactions.stream()
                .filter(t -> t.getBANK_ACCOUNT_ID()
                        .bankAccountId()
                        .equals(bankAccountId.bankAccountId()))
                .toList()
                .getFirst();
    }
}
