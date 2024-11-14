package com.pover.Library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter

public class AdminProfileRequestDto {

    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^\\d{4}$", message = "Password must be 4 digits")
    private String password;

    @NotBlank(message = "Personal number is required")
    @Pattern(
            regexp = "^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\\d{4}$",
            message = "Please enter twelve digits"
    )
    private String member_number;


}
