package com.pover.Library.dto;

import com.pover.Library.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "First name is required")
    private String first_name;

    @NotBlank(message = "Last name is required")
    private String last_name;

    @Email(message = "Use a valid email")
    @NotBlank(message = "Use a valid email")
    private String email;

    @NotBlank(message = "Personal number is required")
    @Pattern(
            regexp = "^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\\d{4}$",
            message = "Please enter twelve digits"
    )
    private String member_number;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^\\d{4}$", message = "Password must be 4 digits")
    private String password;


}
