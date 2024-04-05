package com.domain.stub;

import com.domain.ddd.Stub;
import com.domain.dto.Email;
import com.domain.dto.UserId;
import com.domain.entity.User;
import com.domain.spi.UserSpi;

import java.util.List;
import java.util.Optional;

/**
 * A fake class to perform UserSpi request.
 * <p>
 * Use the @Stub annotation to create a Bean for the framework used in the infrastructure layer for integration test.
 *
 * @see Stub
 */
@Stub
public class UserRepositoryStub implements UserSpi {
    private final DataBaseStub data = new DataBaseStub();
    private final List<User> users = data.users();
    
    @Override
    public List<User> findAll() {
        return users;
    }
    
    @Override
    public Optional<User> findById(UserId userId) {
        User user = users.stream()
                .filter(u -> u.getUSER_ID()
                        .userId()
                        .equals(userId.userId()))
                .toList()
                .getFirst();
        return Optional.of(user);
    }
    
    @Override
    public Optional<User> findByEmail(Email email) {
        User user = users.stream()
                .filter(u -> u.getEmail()
                        .email()
                        .equals(email.email()))
                .toList()
                .getFirst();
        return Optional.of(user);
    }
    
    @Override
    public void deleteById(UserId userId) {
        users.removeIf(u -> u.getUSER_ID()
                .userId()
                .equals(userId.userId()));
    }
    
    @Override
    public User save(User user) {
        if(users.stream()
                .noneMatch(u -> u.getUSER_ID()
                        .equals(user.getUSER_ID()))) {
            users.add(user);
        } else {
            for(int i = 0; i <= users.size() - 1; i++) {
                if(users.get(i)
                        .getUSER_ID()
                        .equals(user.getUSER_ID())) {
                    users.set(i, user);
                }
            }
        }
        return user;
    }
}
