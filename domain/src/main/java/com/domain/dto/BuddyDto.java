package com.domain.dto;

import java.util.UUID;

/**
 * Object to get email from the transfer form.
 * Used to parse the email of a User to add a Buddy.
 */
public record BuddyDto(UUID id, String email) {
}
