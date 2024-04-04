package com.domain;

import com.domain.dto.BankAccountDto;
import com.domain.dto.TransactionDto;
import com.domain.entity.BankAccount;
import com.domain.stub.BankAccountRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountUseCaseTest {

BankAccountRepositoryStub repositoryStub;

BankAccountUseCase bankAccountUseCase;



@BeforeEach
    void setUp(){
    repositoryStub = new BankAccountRepositoryStub();
    bankAccountUseCase = new BankAccountUseCase(repositoryStub);
}

@Test
    void shouldGetAllReturnAllBankAccountTest(){
    List<BankAccount> result = bankAccountUseCase.getAll();
    
    int findAll = repositoryStub.findAll()
            .size();
    
    assertEquals(findAll, result.size());
}

@Test
    void shouldGetByIdReturnBankAccountTest(){
    UUID id = UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de");
    
    BankAccount result = bankAccountUseCase.getById(id);
    
    assertEquals(UUID.fromString("c4755a5e-04c8-448d-a227-5658967d3458"), result.getIBAN());
}

@Test
    void shouldSaveNewBankAccountTest(){
    BankAccount bankAccount = new BankAccount();
    
    BankAccount result = bankAccountUseCase.save(bankAccount);
    
    assertEquals(bankAccount.getID(), result.getID());
}

@Test
    void shouldDeleteABankAccountTest(){
    
    int beforeDelete = repositoryStub.findAll()
            .size();
    
    bankAccountUseCase.deleteById(UUID.fromString("5a518f97-9808-4196-b71a-daf5d8081295"));
    
    int afterDelete = repositoryStub.findAll().size();
    
    assertEquals(afterDelete, beforeDelete - 1);
}

@Test
    void shouldPerformTransactionTest(){
    TransactionDto transactionDto = new TransactionDto("gramps@email.com", new BigDecimal("50.0"), "new description", UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"));
    
    BigDecimal creditAccountBeforeUpdate = repositoryStub.findById(UUID.fromString("f41c1529-c180-4b59-9fc1-1aec8e032658")).get().getBalance();
    BigDecimal debitAccountBeforeUpdate = repositoryStub.findById(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de")).get().getBalance();
    
    bankAccountUseCase.transaction(transactionDto);
    
    BigDecimal creditAccountAfterUpdate = repositoryStub.findById(UUID.fromString("f41c1529-c180-4b59-9fc1-1aec8e032658")).get().getBalance();
    BigDecimal debitAccountAfterUpdate = repositoryStub.findById(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de")).get().getBalance();

    assertEquals(creditAccountAfterUpdate, creditAccountBeforeUpdate.add(new BigDecimal("50.00")));
    assertEquals(debitAccountAfterUpdate, debitAccountBeforeUpdate.subtract(new BigDecimal("52.50")));
}

@Test
    void shouldPerformDepositTest(){
    BankAccountDto bankAccountDto = new BankAccountDto(UUID.fromString("eaf27617-2fd1-421e-a083-991b9fa03bf7"), new BigDecimal("50.00"));
    
    BigDecimal balanceBeforeUpdate = repositoryStub.findById(UUID.fromString("eaf27617-2fd1-421e-a083-991b9fa03bf7"))
            .get()
            .getBalance();
    
    bankAccountUseCase.deposit(bankAccountDto);
    
    BigDecimal balanceAfterUpdate = repositoryStub.findById(UUID.fromString("eaf27617-2fd1-421e-a083-991b9fa03bf7"))
            .get()
            .getBalance();
    
    assertEquals(balanceAfterUpdate, balanceBeforeUpdate.add(new BigDecimal("50.00")));
}

@Test
    void shouldPerformExternalTest(){
    BankAccountDto bankAccountDto = new BankAccountDto(UUID.fromString("eaf27617-2fd1-421e-a083-991b9fa03bf7"), new BigDecimal("50.00"));
    
    BigDecimal balanceBeforeUpdate = repositoryStub.findById(UUID.fromString("eaf27617-2fd1-421e-a083-991b9fa03bf7"))
            .get()
            .getBalance();
    
    bankAccountUseCase.external(bankAccountDto);
    
    BigDecimal balanceAfterUpdate = repositoryStub.findById(UUID.fromString("eaf27617-2fd1-421e-a083-991b9fa03bf7"))
            .get()
            .getBalance();
    
    assertEquals(balanceAfterUpdate, balanceBeforeUpdate.subtract(new BigDecimal("50.00")));
}

@Test
    void shouldReturnTrueWhenBalanceCanPayFeeTest(){
    TransactionDto transactionDto = new TransactionDto("gramps@email.com", new BigDecimal("50.0"), "new description", UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"));
    
    Boolean result = bankAccountUseCase.ableToDeposit(transactionDto);
    
    assertTrue(result);
}
    
    @Test
    void shouldReturnFalseWhenBalanceCantPayFeeTest(){
        TransactionDto transactionDto = new TransactionDto("gramps@email.com", new BigDecimal("500000.0"), "new description", UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"));
        
        Boolean result = bankAccountUseCase.ableToDeposit(transactionDto);
        
        assertFalse(result);
    }

}
