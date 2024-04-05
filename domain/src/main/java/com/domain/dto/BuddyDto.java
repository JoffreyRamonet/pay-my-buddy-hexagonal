package com.domain.dto;

/**
 * Object to get email from the transfer form.
 * Used to parse the email of a User to add a Buddy.
 */
public record BuddyDto(UserId userId, Email email) {
}
