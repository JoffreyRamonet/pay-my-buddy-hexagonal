package com.domain.spi;

import com.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for User entity.
 *
 * @see User
 */
public interface UserSpi {

    List<User> findAll();
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    void deleteById(UUID id);
    User save(User user);
    
}
