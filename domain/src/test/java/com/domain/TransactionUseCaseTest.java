package com.domain;

import com.domain.dto.TransactionDto;
import com.domain.entity.Transaction;
import com.domain.stub.TransactionRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionUseCaseTest {
    
    TransactionRepositoryStub repositoryStub;
    
    TransactionUseCase transactionUseCase;
    
    @BeforeEach
    void setUp() {
        repositoryStub = new TransactionRepositoryStub();
        transactionUseCase = new TransactionUseCase(repositoryStub);
    }
    
    @Test
    void shouldGetAllReturnAllTransactionTest() {
        List<Transaction> result = transactionUseCase.getAll();
        
        int findAll = repositoryStub.findAll()
                .size();
        
        assertEquals(findAll, result.size());
    }
    
    @Test
    void shouldGetByIdReturnTransactionTest() {
        UUID id = UUID.fromString("9a00d550-47d0-40c4-a63d-c00971b69f73");
        
        Transaction result = transactionUseCase.getById(id);
        
        assertEquals("Premier virement.", result.getDescription());
    }
    
    @Test
    void shouldSaveTransactionTest() {
        TransactionDto transactionDto = new TransactionDto("jaboyd@email.com", new BigDecimal("25.00"), "Lorem Ipsum",
                UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"));
        
        Transaction result = transactionUseCase.save(transactionDto);
        
        assertEquals(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"), result.getBANK_ACCOUNT_ID());
        assertNotNull(result.getCREATED_AT());
    }
    
    @Test
    void shouldDeleteByIdTest() {
        int beforeDelete = repositoryStub.findAll()
                .size();
        
        transactionUseCase.deleteById(UUID.fromString("47bc0b9b-3f5d-42f6-9b29-5b71e8033d17"));
        
        int afterDelete = repositoryStub.findAll()
                .size();
        
        assertEquals(afterDelete, beforeDelete - 1);
    }
    
    @Test
    void shouldReturnTheLastTransactionByBankAccountIdTest() {
        UUID id = UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de");
        
        Transaction result = transactionUseCase.getLastTransactionByBankAccountId(id);
        
        assertEquals("9a00d550-47d0-40c4-a63d-c00971b69f73", result.getID());
    }
}
