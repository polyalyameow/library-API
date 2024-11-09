package com.pover.Library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotNull(message = "First name is required")
    private String first_name;

    @NotNull(message = "Last name is required")
    private String last_name;

    @Email(message = "Use a valid email")
    @NotNull
    private String email;

    @NotNull(message = "Personal number is required")
    @Pattern(
            regexp = "^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\\d{4}$",
            message = "Please enter twelve digits"
    )
    private String member_number;
}
