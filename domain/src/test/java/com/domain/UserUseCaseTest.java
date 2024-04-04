package com.domain;

import com.domain.dto.BuddyDto;
import com.domain.dto.DeleteBuddy;
import com.domain.dto.PasswordDto;
import com.domain.dto.UserDto;
import com.domain.dto.UserModifyDto;
import com.domain.entity.User;
import com.domain.stub.UserRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserUseCaseTest {
    
    UserRepositoryStub repositoryStub;
    UserUseCase userUseCase;
    @BeforeEach
    void setUp(){
        repositoryStub = new UserRepositoryStub();
        userUseCase = new UserUseCase(repositoryStub);
    }
    
    @Test
    void shouldGetAllReturnAllUsersTest(){
        int findAll = repositoryStub.findAll()
                .size();
        List<User> result = userUseCase.getAll();
        
        assertEquals(findAll, result.size());
    }
    
    @Test
    void shouldGetByIdReturnUserTest(){
        UUID id = UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f");
        
        User result = userUseCase.getById(id);
        
        assertEquals("jaboyd@email.com", result.getEmail());
    }
    
    @Test
    void shouldGetByEmailReturnUserTest(){
        String email = "jaboyd@email.com";
        
        User result = userUseCase.getByEmail(email);
        
        assertEquals("37c4d6f5-46e6-4265-a5ae-450425306d0f", result.getID().toString());
    }
    
    @Test
    void shouldSaveUserByUserDtoTest(){
        UserDto userDto = new UserDto("drk@email.com", "drk@email.com", "newPass", "newPass", "Jonanathan", "Marrack");
        
        User result = userUseCase.save(userDto);
        
        assertEquals("drk@email.com", result.getEmail());
    }
    
    @Test
    void shouldSaveByUserModifyDtoWhenOnlyFirstNameIsUpdatedTest(){
        UserModifyDto userModifyDto = new UserModifyDto(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002"), "newName", "");
        
        User result = userUseCase.save(userModifyDto);
        
        assertEquals("newName", result.getFirstName());
    }
    
    @Test
    void shouldSaveByUserModifyDtoWhenOnlyLastNameIsUpdatedTest(){
        UserModifyDto userModifyDto = new UserModifyDto(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002"), "", "newLastName");
        
        User result = userUseCase.save(userModifyDto);
        
        assertEquals("newLastName", result.getFirstName());
    }
    
    @Test
    void shouldDeleteUserTest(){
        int beforeDelete = repositoryStub.findAll().size();
        
        userUseCase.deleteAuthenticatedUser(UUID.fromString( "866aad44-6b52-43be-95f3-9f26ccdea918"));
       
        int afterDelete = repositoryStub.findAll().size();
        
        assertEquals(afterDelete, beforeDelete - 1);
    }
    
    @Test
    void shouldSaveNewPasswordByPasswordDtoTest(){
        PasswordDto passwordDto = new PasswordDto(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002"), "Password", "newPass", "newPass");
        
        User result = userUseCase.save(passwordDto);
        
        assertEquals("newPass", result.getPassword());
    }
    
    @Test
    void shouldSaveNewBuddyTest(){
        BuddyDto buddyDto = new BuddyDto(UUID.fromString("677218cd-a42c-4885-95a9-bb9d5006fc17"), "jaboyd@email.com");
        
        int beforeUpdate = repositoryStub.findByEmail("jaboyd@email.com").get().getBuddysIdList().size();
        User result = userUseCase.saveNewBuddy(buddyDto);
        int afterUpdate = repositoryStub.findByEmail("jaboyd@email.com").get().getBuddysIdList().size();
       
        assertEquals(afterUpdate, beforeUpdate + 1);
    }
    
    @Test
    void shouldReturnAllBuddyTest(){
        List<User> result = userUseCase.getAllBuddy(UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f"));
        
        assertEquals(2, result.size());
    }
    
    @Test
    void shouldDeleteABuddyTest(){
        DeleteBuddy deleteBuddy = new DeleteBuddy(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002"), UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f"));
        int beforeDelete = repositoryStub.findById(deleteBuddy.user()).get().getBuddysIdList().size();
        
        userUseCase.deleteBuddy(deleteBuddy);
        
        int afterDelete = repositoryStub.findById(deleteBuddy.user()).get().getBuddysIdList().size();
        
        assertEquals(afterDelete, beforeDelete -1);
    }
    
    

}
