package com.pover.Library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequestDto {
    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    private String email;

}
