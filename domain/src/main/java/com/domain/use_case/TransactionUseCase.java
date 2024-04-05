package com.domain.use_case;

import com.domain.api.TransactionApi;
import com.domain.ddd.DomainService;
import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionDto;
import com.domain.dto.TransactionId;
import com.domain.entity.BankAccount;
import com.domain.entity.Transaction;
import com.domain.spi.TransactionSpi;
import com.domain.use_case.errors.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DomainService
public class TransactionUseCase implements TransactionApi {
    
    private final static Logger logger = LoggerFactory.getLogger(TransactionUseCase.class);
    
    private final TransactionSpi transactionSpi;
    private final BankAccountUseCase bankAccountUseCase;
    private final UserUseCase userUseCase;
    
    public TransactionUseCase(TransactionSpi transactionSpi, BankAccountUseCase bankAccountUseCase,
                              UserUseCase userUseCase) {
        this.transactionSpi = transactionSpi;
        
        this.bankAccountUseCase = bankAccountUseCase;
        this.userUseCase = userUseCase;
    }
    
    @Override
    public List<Transaction> getAll() {
        return transactionSpi.findAll();
    }
    
    @Override
    public Transaction getById(TransactionId transactionId) {
        logger.debug("Id parsed: " + transactionId.transactionId());
        Optional<Transaction> transactionFound = transactionSpi.findById(transactionId);
        logger.debug("Optional is empty: " + transactionFound.isEmpty());
        
        if(transactionFound.isEmpty()) {
            throw new DataNotFoundException("No transaction found.");
        } else {
            return transactionFound.get();
        }
    }
    
    @Override
    public Transaction save(TransactionDto transactionDto) {
        logger.debug(
                "TransactionDto parsed: connection: " + transactionDto.connection() + " - " + transactionDto.amount() +
                        " - " + transactionDto.description() + " - " + transactionDto.bankAccountId());
        return new Transaction(userUseCase.getByEmail(transactionDto.connection())
                .getUSER_ID()
                .userId(), transactionDto.amount(), transactionDto.description(), transactionDto.bankAccountId());
    }
    
    @Override
    public void deleteById(TransactionId transactionId) {
        logger.debug("Id parsed: " + transactionId.transactionId());
        transactionSpi.deleteById(transactionId);
    }
    
    @Override
    public Transaction getLastTransactionByBankAccountId(BankAccountId bankAccountId) {
        logger.debug("Id parsed: " + bankAccountId.bankAccountId());
        return transactionSpi.findTheLastTransactionByBankAccountId(bankAccountId);
    }
    
    @Override
    public void transaction(TransactionDto transactionDto) {
        logger.debug(
                "TransactionDto parsed: connection: " + transactionDto.connection() + " - " + transactionDto.amount() +
                        " - " + transactionDto.description() + " - " + transactionDto.bankAccountId());
        
        BigDecimal amount = transactionDto.amount();
        BigDecimal fee = bankAccountUseCase.feeCalculator(amount);
        BankAccount bankAccountToCredit = bankAccountUseCase.getById(new BankAccountId(
                userUseCase.getByEmail(transactionDto.connection())
                        .getBANK_ACCOUNT_ID()));
        BankAccount bankAccountToDebit = bankAccountUseCase.getById(new BankAccountId(transactionDto.bankAccountId()
                .bankAccountId()));
        
        bankAccountUseCase.creditBankAccount(bankAccountToCredit, amount);
        
        feeCollect(bankAccountUseCase.getById(new BankAccountId(transactionDto.bankAccountId()
                .bankAccountId())), fee);
        
        bankAccountUseCase.debitBankAccount(bankAccountToDebit, amount);
    }
    
    
    private void feeCollect(BankAccount bankAccount, BigDecimal amount) {
        BankAccount feeAccount = bankAccountUseCase.getAll()
                .getFirst();
        
        if(feeAccount != null) {
            logger.debug("feeAccount balance before: " + feeAccount.getBalance());
            feeAccount.setBalance(feeAccount.getBalance()
                    .add(amount));
            logger.debug("feeAccount after: " + feeAccount.getBalance());
            bankAccountUseCase.save(feeAccount);
        } else {
            throw new DataNotFoundException("Fee account not found.");
        }
        
        logger.debug("Principal bankAccount balance before: " + bankAccount.getBalance());
        bankAccount.setBalance(bankAccount.getBalance()
                .subtract(amount));
        logger.debug("Principal bankAccount balance after: " + bankAccount.getBalance());
        bankAccountUseCase.save(bankAccount);
    }
}
