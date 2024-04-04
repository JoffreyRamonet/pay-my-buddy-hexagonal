package com.domain.stub;

import com.domain.ddd.Stub;
import com.domain.entity.User;
import com.domain.spi.UserSpi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<User> findById(UUID id) {
        User user = users.stream()
                .filter(u -> u.getID()
                        .equals(id))
                .toList()
                .getFirst();
        return Optional.of(user);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        User user = users.stream()
                .filter(u -> u.getEmail()
                        .equals(email))
                .toList()
                .getFirst();
        return Optional.of(user);
    }
    
    @Override
    public void deleteById(UUID id) {
    users.removeIf(u -> u.getID().equals(id));
    }
    
    @Override
    public User save(User user) {
       if(users.stream()
                .noneMatch(u -> u.getID().equals(user.getID()))){
           users.add(user);
       } else {
           for(int i = 0; i <= users.size(); i++){
               if(users.get(i).getID().equals(user.getID())){
                   users.set(i, user);
               }
           }
       }
        return user;
    }
}
