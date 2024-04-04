package com.domain.dto;

import java.util.UUID;

/**
 * Object to get the data input from the profileModify form.
 * Used to parse data to update the Principal User's firstname, lastname and/or email attribute.
 */
public record UserModifyDto(UUID id, String firstName, String lastName) {
}
