package com.sitalk.api.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String code;
    private String provider;
}
