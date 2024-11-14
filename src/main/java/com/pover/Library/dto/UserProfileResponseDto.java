package com.pover.Library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResponseDto {

    private String first_name;
    private String last_name;
    private String email;

    public UserProfileResponseDto(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

}
