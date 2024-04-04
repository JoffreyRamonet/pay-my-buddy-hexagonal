package com.domain.api;

import com.domain.dto.BuddyDto;
import com.domain.dto.PasswordDto;
import com.domain.dto.UserDto;
import com.domain.dto.UserModifyDto;
import com.domain.entity.User;

import java.util.List;
import java.util.UUID;
/**
 * Interface to perform requests from controllers to the domain for User entity.
 *
 * @see User
 */
public interface UserApi {
    List<User> getAll();
    User getById(UUID id);
    User getByEmail(String email);
    void deleteAuthenticatedUser();
    User save(UserDto userDto);
    User save(UserModifyDto userModifyDto);
    User save(PasswordDto passwordDto);
    User saveNewBuddy(BuddyDto buddyDto);
    User getTheAuthenticatedUser();
    List<User> getAllBuddy(User user);
    User deleteBuddy(UUID id);
    User getUserByBuddyDto(BuddyDto buddyDto);
}
