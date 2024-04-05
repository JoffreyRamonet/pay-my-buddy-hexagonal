package com.domain;

import com.domain.dto.Email;
import com.domain.dto.BankAccountId;
import com.domain.dto.TransactionDto;
import com.domain.dto.TransactionId;
import com.domain.entity.Transaction;
import com.domain.stub.BankAccountRepositoryStub;
import com.domain.stub.TransactionRepositoryStub;
import com.domain.stub.UserRepositoryStub;
import com.domain.use_case.BankAccountUseCase;
import com.domain.use_case.TransactionUseCase;
import com.domain.use_case.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionUseCaseTest {
    
    TransactionRepositoryStub transactionRepositoryStub;
    BankAccountRepositoryStub bankAccountRepositoryStub;
    UserRepositoryStub userRepositoryStub;
    TransactionUseCase transactionUseCase;
    BankAccountUseCase bankAccountUseCase;
    UserUseCase userUseCase;
    
    @BeforeEach
    void setUp() {
        transactionRepositoryStub = new TransactionRepositoryStub();
        bankAccountRepositoryStub = new BankAccountRepositoryStub();
        userRepositoryStub = new UserRepositoryStub();
        bankAccountUseCase = new BankAccountUseCase(bankAccountRepositoryStub);
        userUseCase = new UserUseCase(userRepositoryStub, bankAccountRepositoryStub);
        transactionUseCase = new TransactionUseCase(transactionRepositoryStub, bankAccountUseCase, userUseCase);
    }
    
    @Test
    void shouldGetAllReturnAllTransactionTest() {
        List<Transaction> result = transactionUseCase.getAll();
        
        int findAll = transactionRepositoryStub.findAll()
                .size();
        
        assertEquals(findAll, result.size());
    }
    
    @Test
    void shouldGetByIdReturnTransactionTest() {
        TransactionId transactionId = new TransactionId(UUID.fromString("9a00d550-47d0-40c4-a63d-c00971b69f73"));
        
        Transaction result = transactionUseCase.getById(transactionId);
        
        assertEquals("Premier virement.", result.getDescription());
    }
    
    @Test
    void shouldSaveTransactionTest() {
        TransactionDto transactionDto =
                new TransactionDto(new Email("jaboyd@email.com"), new BigDecimal("25.00"), "Lorem Ipsum",
                        new BankAccountId(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de")));
        
        Transaction result = transactionUseCase.save(transactionDto);
        
        assertEquals(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"), result.getBANK_ACCOUNT_ID()
                .bankAccountId());
        assertNotNull(result.getCREATED_AT());
    }
    
    @Test
    void shouldDeleteByIdTest() {
        int beforeDelete = transactionRepositoryStub.findAll()
                .size();
        
        transactionUseCase.deleteById(new TransactionId(UUID.fromString("47bc0b9b-3f5d-42f6-9b29-5b71e8033d17")));
        
        int afterDelete = transactionRepositoryStub.findAll()
                .size();
        
        assertEquals(beforeDelete - 1, afterDelete);
    }
    
    @Test
    void shouldReturnTheLastTransactionByBankAccountIdTest() {
        BankAccountId id = new BankAccountId(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"));
        
        Transaction result = transactionUseCase.getLastTransactionByBankAccountId(id);
        
        assertEquals(UUID.fromString("9a00d550-47d0-40c4-a63d-c00971b69f73"), result.getID()
                .transactionId());
    }
    
    @Test
    void shouldPerformTransactionTest() {
        TransactionDto transactionDto =
                new TransactionDto(new Email("gramps@email.com"), new BigDecimal("50.00"), "new description",
                        new BankAccountId(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de")));
        
        BankAccountId creditId = new BankAccountId(UUID.fromString("f41c1529-c180-4b59-9fc1-1aec8e032658"));
        BankAccountId debitId = new BankAccountId(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"));
        
        BigDecimal creditAccountBeforeUpdate = bankAccountRepositoryStub.findById(creditId)
                .get()
                .getBalance();
        BigDecimal debitAccountBeforeUpdate = bankAccountRepositoryStub.findById(debitId)
                .get()
                .getBalance();
        
        transactionUseCase.transaction(transactionDto);
        
        BigDecimal creditAccountAfterUpdate = bankAccountRepositoryStub.findById(creditId)
                .get()
                .getBalance();
        BigDecimal debitAccountAfterUpdate = bankAccountRepositoryStub.findById(debitId)
                .get()
                .getBalance();
        
        assertEquals(new DecimalFormat("#0.00").format(creditAccountAfterUpdate),
                new DecimalFormat("#0.00").format(creditAccountBeforeUpdate.add(new BigDecimal("50.00"))));
        assertEquals(new DecimalFormat("#0.00").format(debitAccountAfterUpdate),
                new DecimalFormat("#0.00").format(debitAccountBeforeUpdate.subtract(new BigDecimal("52.50"))));
    }
}
