package com.domain.dto;

import java.util.UUID;

/**
 * Object to get the data input from the passwordModify form.
 * Used to parse data to update the Principal User's password attribute.
 */
public record PasswordDto(UUID id, String oldPassword, String newPassword, String newPasswordCheck) {
}
