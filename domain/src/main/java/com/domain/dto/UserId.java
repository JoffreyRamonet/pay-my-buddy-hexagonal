package com.domain.dto;

import java.util.UUID;

/**
 * Contains the UUID of an User object.
 *
 * @param userId the id
 * @see com.domain.entity.User
 */
public record UserId(UUID userId) {
}
