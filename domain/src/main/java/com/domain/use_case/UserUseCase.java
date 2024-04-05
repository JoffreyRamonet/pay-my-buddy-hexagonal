package com.domain.use_case;

import com.domain.api.UserApi;
import com.domain.ddd.DomainService;
import com.domain.dto.BuddyDto;
import com.domain.dto.DeleteBuddy;
import com.domain.dto.Email;
import com.domain.dto.PasswordDto;
import com.domain.dto.UserDto;
import com.domain.dto.UserId;
import com.domain.dto.UserModifyDto;
import com.domain.entity.BankAccount;
import com.domain.entity.User;
import com.domain.spi.BankAccountSpi;
import com.domain.spi.UserSpi;
import com.domain.use_case.errors.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@DomainService
public class UserUseCase implements UserApi {
    
    private final static Logger logger = LoggerFactory.getLogger(UserUseCase.class);
    private final UserSpi userSpi;
    private final BankAccountSpi bankAccountSpi;
    
    public UserUseCase(UserSpi userSpi, BankAccountSpi bankAccountSpi) {
        this.userSpi = userSpi;
        this.bankAccountSpi = bankAccountSpi;
    }
    
    @Override
    public List<User> getAll() {
        return userSpi.findAll();
    }
    
    @Override
    public User getById(UserId userId) {
        logger.debug("Id parsed: " + userId.userId());
        Optional<User> userFound = userSpi.findById(userId);
        logger.debug("Optional is empty: " + userFound.isEmpty());
        
        if(userFound.isEmpty()) {
            throw new DataNotFoundException("No user found.");
        } else {
            return userFound.get();
        }
    }
    
    @Override
    public User getByEmail(Email email) {
        logger.debug("Email parsed: " + email);
        Optional<User> userFound = userSpi.findByEmail(email);
        logger.debug("Optional is empty: " + userFound.isEmpty());
        
        if(userFound.isEmpty()) {
            throw new DataNotFoundException("No user found.");
        } else {
            return userFound.get();
        }
    }
    
    @Override
    public void deleteAuthenticatedUser(UserId userId) {
        logger.debug("Id parsed: " + userId.userId());
        userSpi.deleteById(userId);
    }
    
    @Override
    public User save(UserDto userDto) {
        logger.debug("UserDto parsed: " + userDto.email()
                .email() + " - " + userDto.firstName() + " - " + userDto.lastName() + " - " + userDto.password());
        
        BankAccount bankAccount = new BankAccount();
        bankAccountSpi.save(bankAccount);
        return userSpi.save(new User(userDto.email(), userDto.password(), userDto.firstName(), userDto.lastName(),
                bankAccount.getBankAccountId()
                        .bankAccountId()));
    }
    
    @Override
    public User save(UserModifyDto userModifyDto) {
        logger.debug("UserModifyDto parsed: " + userModifyDto.firstName() + " - " + userModifyDto.lastName());
        
        User user = getById(userModifyDto.userId());
        
        if(!userModifyDto.firstName()
                .isEmpty()) {
            user.setFirstName(userModifyDto.firstName());
        }
        
        if(!userModifyDto.lastName()
                .isEmpty()) {
            user.setLastName(userModifyDto.lastName());
        }
        
        return userSpi.save(user);
    }
    
    @Override
    public User save(PasswordDto passwordDto) {
        logger.debug("passwordDto: " + passwordDto.newPassword());
        
        User user = getById(passwordDto.userId());
        
        user.setPassword(passwordDto.newPassword());
        
        return userSpi.save(user);
    }
    
    @Override
    public User saveNewBuddy(BuddyDto buddyDto) {
        logger.debug("BuddyDto parsed: " + buddyDto.userId() + " - " + buddyDto.email());
        
        User user = getById(buddyDto.userId());
        User buddy = getByEmail(buddyDto.email());
        
        List<UUID> buddysIdList = user.getBuddysIdList();
        logger.debug("buddyList before add: " + buddysIdList.size());
        buddysIdList.add(buddy.getUSER_ID()
                .userId());
        logger.debug("buddyList after add: " + buddysIdList.size());
        
        user.setBuddysIdList(buddysIdList);
        
        return userSpi.save(user);
    }
    
    @Override
    public List<User> getAllBuddy(UserId userId) {
        logger.debug("Id parsed: " + userId.userId());
        User user = getById(userId);
        
        List<User> result = new ArrayList<>();
        
        for(UUID uuid : user.getBuddysIdList()) {
            result.add(getById(userId));
        }
        logger.debug("List of buddys size: " + result.size());
        
        return result;
    }
    
    @Override
    public User deleteBuddy(DeleteBuddy deleteBuddy) {
        logger.debug("The deleteBuddy parsed: " + deleteBuddy.userId()
                .userId() + " - " + deleteBuddy.buddyId()
                .userId());
        User user = getById(deleteBuddy.userId());
        
        List<UUID> buddysIdList = user.getBuddysIdList();
        logger.debug("buddyList before add: " + buddysIdList.size());
        buddysIdList.remove(deleteBuddy.buddyId()
                .userId());
        logger.debug("buddyList after add: " + buddysIdList.size());
        
        user.setBuddysIdList(buddysIdList);
        return userSpi.save(user);
    }
}
