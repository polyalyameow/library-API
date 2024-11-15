package com.pover.Library.dto;

import com.pover.Library.validation.CreateValidationGroup;
import com.pover.Library.validation.UpdateValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

public class AdminProfileRequestDto {

    @NotBlank(groups = CreateValidationGroup.class)
    private String first_name;

    @NotBlank(groups = CreateValidationGroup.class)
    private String last_name;

    @NotBlank(groups = CreateValidationGroup.class)
    private String email;

    @Pattern(regexp = "^\\d{4}$", message = "Password must be 4 digits", groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private String password;

    @Pattern(
            regexp = "^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])\\d{4}$",
            message = "Please enter twelve digits",
            groups = {CreateValidationGroup.class, UpdateValidationGroup.class}
    )
    private String member_number;

}
