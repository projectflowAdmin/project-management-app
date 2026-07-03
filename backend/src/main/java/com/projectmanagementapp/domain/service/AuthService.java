package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.dto.LoginRequest;
import com.projectmanagementapp.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
