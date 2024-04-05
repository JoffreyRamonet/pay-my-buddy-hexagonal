package com.domain.entity;

import com.domain.dto.Email;
import com.domain.dto.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User model.
 * Used to stock all data of the user. Is create at the registration.
 * email is used to identify the user on the login page.
 * password must be stocked encrypted by BCryptPasswordEncode.
 * role is always USER.
 * bank_account_id is the id of this user's BankAccount's.
 * buddysIdList is the list of all UUID of this user connections.
 */
public class User {
    
    private final UserId USER_ID;
    private Email email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private final UUID BANK_ACCOUNT_ID;
    private List<UUID> buddysIdList;
    
    public User(UserId USER_ID, Email email, String password, String firstName, String lastName, UUID bankAccountId,
                List<UUID> buddysIdList) {
        this.USER_ID = USER_ID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = "USER";
        this.BANK_ACCOUNT_ID = bankAccountId;
        this.buddysIdList = buddysIdList;
    }
    
    public User(Email email, String password, String firstName, String lastName, UUID bankAccountId) {
        this.USER_ID = new UserId(UUID.randomUUID());
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = "USER";
        this.BANK_ACCOUNT_ID = bankAccountId;
        this.buddysIdList = new ArrayList<>();
    }
    
    public User(UserId USER_ID, Email email, String password, String firstName, String lastName, String role,
                UUID BANK_ACCOUNT_ID, List<UUID> buddysIdList) {
        this.USER_ID = USER_ID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.BANK_ACCOUNT_ID = BANK_ACCOUNT_ID;
        this.buddysIdList = buddysIdList;
    }
    
    
    public UserId getUSER_ID() {
        return USER_ID;
    }
    
    public Email getEmail() {
        return email;
    }
    
    public void setEmail(Email email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public UUID getBANK_ACCOUNT_ID() {
        return BANK_ACCOUNT_ID;
    }
    
    public List<UUID> getBuddysIdList() {
        return buddysIdList;
    }
    
    public void setBuddysIdList(List<UUID> buddysIdList) {
        this.buddysIdList = buddysIdList;
    }
}
