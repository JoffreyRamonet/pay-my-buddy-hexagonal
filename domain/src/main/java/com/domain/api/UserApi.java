package com.domain.api;

import com.domain.dto.BuddyDto;
import com.domain.dto.DeleteBuddy;
import com.domain.dto.Email;
import com.domain.dto.PasswordDto;
import com.domain.dto.UserDto;
import com.domain.dto.UserId;
import com.domain.dto.UserModifyDto;
import com.domain.entity.User;

import java.util.List;

/**
 * Interface to perform requests from controllers to the domain for User entity.
 *
 * @see User
 */
public interface UserApi {
    List<User> getAll();
    
    User getById(UserId userId);
    
    User getByEmail(Email email);
    
    void deleteAuthenticatedUser(UserId userId);
    
    User save(UserDto userDto);
    
    User save(UserModifyDto userModifyDto);
    
    User save(PasswordDto passwordDto);
    
    User saveNewBuddy(BuddyDto buddyDto);
    
    List<User> getAllBuddy(UserId userId);
    
    User deleteBuddy(DeleteBuddy deleteBuddy);
}
