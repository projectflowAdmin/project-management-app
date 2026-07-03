package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.dto.LoginRequest;
import com.projectmanagementapp.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public LoginResponse login(LoginRequest request) {
        String token = "demo-token-for-" + request.getEmail().replace("@", "_at_");
        return new LoginResponse("Demo User", token, "ログインに成功しました");
    }
}
