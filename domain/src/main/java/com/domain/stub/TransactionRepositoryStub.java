package com.domain.stub;

import com.domain.ddd.Stub;
import com.domain.entity.Transaction;
import com.domain.spi.TransactionSpi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Transaction> findById(UUID id) {
        Transaction transaction = transactions.stream()
                .filter(t -> t.getID()
                        .equals(id))
                .toList()
                .getFirst();
        return Optional.of(transaction);
    }
    
    @Override
    public Transaction save(Transaction transaction) {
        if(transactions.stream().noneMatch(t -> t.getID().equals(transaction.getID()))){
            transactions.add(transaction);
        } else {
            for(int i = 0; i <= transactions.size() ; i++){
                if(transactions.get(i).getID().equals(transaction.getID())){
                    transactions.set(i, transaction);
                }
            }
        }
        
        return null;
    }
    
    @Override
    public void deleteById(UUID id) {
    transactions.removeIf(t -> t.getID().equals(id));
    }
    
    @Override
    public Transaction findTheLastTransactionByBankAccountId(UUID id) {
        return null;
    }
}
