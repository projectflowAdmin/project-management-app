package com.projectmanagementapp.dto;

import lombok.Value;

@Value
public class LoginResponse {
    String userName;
    String token;
    String message;
}
