package com.pover.Library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminProfileRequestDto {

    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String member_number;


}
