package com.domain.spi;

import com.domain.dto.Email;
import com.domain.dto.UserId;
import com.domain.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for User entity.
 *
 * @see User
 */
public interface UserSpi {
    
    List<User> findAll();
    
    Optional<User> findById(UserId userId);
    
    Optional<User> findByEmail(Email email);
    
    void deleteById(UserId userId);
    
    User save(User user);
}
