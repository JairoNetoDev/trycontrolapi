package com.trycontrol.user.dto;

import java.time.LocalDateTime;

public record UserDTO(
    String name,
    String email,
    String password
) {
}
