package com.domain;

import com.domain.dto.BuddyDto;
import com.domain.dto.DeleteBuddy;
import com.domain.dto.Email;
import com.domain.dto.PasswordDto;
import com.domain.dto.UserDto;
import com.domain.dto.UserId;
import com.domain.dto.UserModifyDto;
import com.domain.entity.User;
import com.domain.stub.BankAccountRepositoryStub;
import com.domain.stub.UserRepositoryStub;
import com.domain.use_case.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserUseCaseTest {
    
    UserRepositoryStub userRepositoryStub;
    BankAccountRepositoryStub bankAccountRepositoryStub;
    UserUseCase userUseCase;
    
    @BeforeEach
    void setUp() {
        userRepositoryStub = new UserRepositoryStub();
        bankAccountRepositoryStub = new BankAccountRepositoryStub();
        userUseCase = new UserUseCase(userRepositoryStub, bankAccountRepositoryStub);
    }
    
    @Test
    void shouldGetAllReturnAllUsersTest() {
        int findAll = userRepositoryStub.findAll()
                .size();
        List<User> result = userUseCase.getAll();
        
        assertEquals(findAll, result.size());
    }
    
    @Test
    void shouldGetByIdReturnUserTest() {
        UUID id = UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f");
        
        User result = userUseCase.getById(new UserId(id));
        
        assertEquals("jaboyd@email.com", result.getEmail()
                .email());
    }
    
    @Test
    void shouldGetByEmailReturnUserTest() {
        Email email = new Email("jaboyd@email.com");
        
        User result = userUseCase.getByEmail(email);
        
        assertEquals("37c4d6f5-46e6-4265-a5ae-450425306d0f", result.getUSER_ID()
                .userId()
                .toString());
    }
    
    @Test
    void shouldSaveUserByUserDtoTest() {
        Email email = new Email("drk@email.com");
        UserDto userDto = new UserDto(email, email, "newPass", "newPass", "Jonanathan", "Marrack");
        
        User result = userUseCase.save(userDto);
        
        assertEquals("drk@email.com", result.getEmail()
                .email());
    }
    
    @Test
    void shouldSaveByUserModifyDtoWhenOnlyFirstNameIsUpdatedTest() {
        UserModifyDto userModifyDto =
                new UserModifyDto(new UserId(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002")), "newName", "");
        
        User result = userUseCase.save(userModifyDto);
        
        assertEquals("newName", result.getFirstName());
    }
    
    @Test
    void shouldSaveByUserModifyDtoWhenOnlyLastNameIsUpdatedTest() {
        UserModifyDto userModifyDto =
                new UserModifyDto(new UserId(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002")), "",
                        "newLastName");
        
        User result = userUseCase.save(userModifyDto);
        
        assertEquals("newLastName", result.getLastName());
    }
    
    @Test
    void shouldDeleteUserTest() {
        int beforeDelete = userRepositoryStub.findAll()
                .size();
        
        userUseCase.deleteAuthenticatedUser(new UserId(UUID.fromString("866aad44-6b52-43be-95f3-9f26ccdea918")));
        
        int afterDelete = userRepositoryStub.findAll()
                .size();
        
        assertEquals(afterDelete, beforeDelete - 1);
    }
    
    @Test
    void shouldSaveNewPasswordByPasswordDtoTest() {
        PasswordDto passwordDto =
                new PasswordDto(new UserId(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002")), "Password",
                        "newPass", "newPass");
        
        User result = userUseCase.save(passwordDto);
        
        assertEquals("newPass", result.getPassword());
    }
    
    @Test
    void shouldSaveNewBuddyTest() {
        UserId userId = new UserId(UUID.fromString("677218cd-a42c-4885-95a9-bb9d5006fc17"));
        BuddyDto buddyDto = new BuddyDto(userId, new Email("jaboyd@email.com"));
        
        int beforeUpdate = userRepositoryStub.findById(userId)
                .get()
                .getBuddysIdList()
                .size();
        userUseCase.saveNewBuddy(buddyDto);
        int afterUpdate = userRepositoryStub.findById(userId)
                .get()
                .getBuddysIdList()
                .size();
        
        assertEquals(beforeUpdate + 1, afterUpdate);
    }
    
    @Test
    void shouldReturnAllBuddyTest() {
        List<User> result =
                userUseCase.getAllBuddy(new UserId(UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f")));
        
        assertEquals(2, result.size());
    }
    
    @Test
    void shouldDeleteABuddyTest() {
        DeleteBuddy deleteBuddy = new DeleteBuddy(new UserId(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002")),
                new UserId(UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f")));
        int beforeDelete = userRepositoryStub.findById(deleteBuddy.userId())
                .get()
                .getBuddysIdList()
                .size();
        
        userUseCase.deleteBuddy(deleteBuddy);
        
        int afterDelete = userRepositoryStub.findById(deleteBuddy.userId())
                .get()
                .getBuddysIdList()
                .size();
        
        assertEquals(afterDelete, beforeDelete - 1);
    }
}
