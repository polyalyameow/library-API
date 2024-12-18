package com.pover.Library.dto;

import com.pover.Library.model.enums.Role;
import jakarta.persistence.Enumerated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequestDto {
    @NotNull(message = "Username is required and unique")
    private String username;
    @NotNull(message = "Password is required")
    private String password;

    @Enumerated
    private Role role;
}
