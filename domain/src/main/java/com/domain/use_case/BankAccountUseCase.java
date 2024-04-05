package com.domain.use_case;

import com.domain.api.BankAccountApi;
import com.domain.ddd.DomainService;
import com.domain.dto.BankAccountDto;
import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionDto;
import com.domain.entity.BankAccount;
import com.domain.spi.BankAccountSpi;
import com.domain.use_case.errors.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DomainService
public class BankAccountUseCase implements BankAccountApi {
    
    private final static Logger logger = LoggerFactory.getLogger(BankAccountUseCase.class);
    private final BankAccountSpi bankAccountSpi;
    
    
    public BankAccountUseCase(BankAccountSpi bankAccountSpi) {
        this.bankAccountSpi = bankAccountSpi;
    }
    
    @Override
    public List<BankAccount> getAll() {
        return bankAccountSpi.findAll();
    }
    
    @Override
    public BankAccount getById(BankAccountId bankAccountId) {
        logger.debug("Id parsed: " + bankAccountId.bankAccountId());
        Optional<BankAccount> bankAccountFound = bankAccountSpi.findById(bankAccountId);
        logger.debug("Optional is empty: " + bankAccountFound.isEmpty());
        
        if(bankAccountFound.isEmpty()) {
            throw new DataNotFoundException("No BankAccount found.");
        } else {
            return bankAccountFound.get();
        }
    }
    
    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountSpi.save(bankAccount);
    }
    
    @Override
    public void deleteById(BankAccountId bankAccountId) {
        bankAccountSpi.deleteById(bankAccountId);
    }
    
    @Override
    public void deposit(BankAccountDto bankAccountDto) {
        logger.debug("BankAccountDto parsed: " + bankAccountDto.bankAccountId() + " - " + bankAccountDto.amount());
        
        BankAccount bankAccount = getById(bankAccountDto.bankAccountId());
        
        logger.debug("Balance before deposit: " + bankAccount.getBalance());
        bankAccount.setBalance(bankAccount.getBalance()
                .add(bankAccountDto.amount()));
        logger.debug("Balance after deposit: " + bankAccount.getBalance());
        
        bankAccountSpi.save(bankAccount);
    }
    
    @Override
    public void external(BankAccountDto bankAccountDto) {
        logger.debug("BankAccountDto parsed: " + bankAccountDto.bankAccountId() + " - " + bankAccountDto.amount());
        
        BankAccount bankAccount = getById(bankAccountDto.bankAccountId());
        
        logger.debug("Balance before external: " + bankAccount.getBalance());
        bankAccount.setBalance(bankAccount.getBalance()
                .subtract(bankAccountDto.amount()));
        logger.debug("Balance after external: " + bankAccount.getBalance());
        
        bankAccountSpi.save(bankAccount);
    }
    
    @Override
    public Boolean ableToPay(TransactionDto transactionDto) {
        logger.debug("TransactionDto parsed: bankAccount id: " + transactionDto.bankAccountId() + " the amount: " +
                transactionDto.amount());
        BigDecimal amount = transactionDto.amount();
        BigDecimal fee = feeCalculator(amount);
        BigDecimal totalRequiredAmount = amount.add(fee);
        logger.debug("Fee amount: " + fee + " total required: " + totalRequiredAmount);
        BankAccount bankAccount = getById(new BankAccountId(transactionDto.bankAccountId()
                .bankAccountId()));
        
        BigDecimal balance = bankAccount.getBalance()
                .subtract(totalRequiredAmount);
        logger.debug("Balance after fee: " + balance);
        return (balance.compareTo(BigDecimal.ZERO) > 0);
    }
    
    BigDecimal feeCalculator(BigDecimal amount) {
        BigDecimal fee = new BigDecimal("0.05");
        logger.debug("Amount parsed: " + amount);
        return amount.multiply(fee);
    }
    
    void creditBankAccount(BankAccount bankAccountToCredit, BigDecimal amount) {
        logger.debug("BankAccount id: " + bankAccountToCredit.getBankAccountId() + " balance before: " +
                bankAccountToCredit.getBalance());
        
        bankAccountToCredit.setBalance(bankAccountToCredit.getBalance()
                .add(amount));
        
        logger.debug("Balance after: " + bankAccountToCredit.getBalance());
        
        bankAccountSpi.save(bankAccountToCredit);
    }
    
    void debitBankAccount(BankAccount bankAccountToDebit, BigDecimal amount) {
        logger.debug("BankAccount id: " + bankAccountToDebit.getBankAccountId() + " balance before: " +
                bankAccountToDebit.getBalance());
        
        bankAccountToDebit.setBalance(bankAccountToDebit.getBalance()
                .subtract(amount));
        
        logger.debug("Balance after: " + bankAccountToDebit.getBalance());
        
        bankAccountSpi.save(bankAccountToDebit);
    }
}
