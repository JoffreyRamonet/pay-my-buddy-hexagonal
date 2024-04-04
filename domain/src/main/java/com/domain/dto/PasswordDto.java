package com.domain.dto;

/**
 * Object to get the data input from the passwordModify form.
 * Used to parse data to update the Principal User's password attribute.
 */
public record PasswordDto(String oldPassword, String newPassword, String newPasswordCheck) {
}
