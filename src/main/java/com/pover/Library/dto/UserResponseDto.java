package com.pover.Library.dto;

import com.pover.Library.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long user_id;
    private String first_name;
    private String last_name;
    private String member_number;
    private Role role;

    public UserResponseDto(Long user_id, String first_name, String last_name, String member_number) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.member_number = member_number;
        this.role = Role.USER;

    }

}
