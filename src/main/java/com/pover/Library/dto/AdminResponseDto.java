package com.pover.Library.dto;

import com.pover.Library.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDto {
    private Long admin_id;
    private String username;
    private Role role;

    public AdminResponseDto(Long admin_id, String username, Role role) {
        this.admin_id = admin_id;
        this.username = username;
        this.role = role;
    }
}
