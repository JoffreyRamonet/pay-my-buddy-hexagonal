package com.domain.dto;

/**
 * Object to get the data input from the profileModify form.
 * Used to parse data to update the Principal User's firstname, lastname and/or email attribute.
 */
public record UserModifyDto(UserId userId, String firstName, String lastName) {
}
