package com.pover.Library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAdminLoginDto {

    private String token;

    public ResponseAdminLoginDto(String token) {
        this.token = token;
    }




}

