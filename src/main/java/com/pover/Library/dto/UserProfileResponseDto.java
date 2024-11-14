package com.pover.Library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfileResponseDto {

    private String first_name;
    private String last_name;
    private String email;
    private List<LoanResponseDto> activeLoans;

    public UserProfileResponseDto(String first_name, String last_name, String email, List<LoanResponseDto> activeLoans) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.activeLoans = activeLoans;
    }

}
