package com.domain.entity;

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
    
    private final UUID ID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private final UUID BANK_ACCOUNT_ID;
    private List<UUID> buddysIdList;
    
    public User(UUID id, String email, String password, String firstName, String lastName,
                UUID bankAccountId, List<UUID> buddysIdList) {
        this.ID = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = "USER";
        this.BANK_ACCOUNT_ID = bankAccountId;
        this.buddysIdList = buddysIdList;
    }
    
    public User(String email, String password, String firstName, String lastName, UUID bankAccountId) {
        this.ID = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = "USER";
        this.BANK_ACCOUNT_ID = bankAccountId;
        this.buddysIdList = new ArrayList<>();
    }
    
    public User(UUID ID, String email, String password, String firstName, String lastName, String role,
                UUID BANK_ACCOUNT_ID, List<UUID> buddysIdList) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.BANK_ACCOUNT_ID = BANK_ACCOUNT_ID;
        this.buddysIdList = buddysIdList;
    }
    
    
    public UUID getID() {
        return ID;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
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
