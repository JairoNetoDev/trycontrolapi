package com.trycontrol.user.dto;

import java.time.LocalDateTime;

public record UserDTO(
    String email,
    String password,
    String name
) {
}
