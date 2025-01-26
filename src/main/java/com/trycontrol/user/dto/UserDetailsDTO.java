package com.trycontrol.user.dto;

import java.time.LocalDateTime;

public record UserDetailsDTO(
    String name,
    String email,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
