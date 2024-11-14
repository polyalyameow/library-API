package com.pover.Library.dto;

public class AdminProfileResponseDto {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String member_number;

    public AdminProfileResponseDto(String first_name, String last_name, String email, String password, String member_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.member_number = member_number;
    }
}
