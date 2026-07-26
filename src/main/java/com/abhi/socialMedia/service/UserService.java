package com.abhi.socialMedia.service;

import com.abhi.socialMedia.dto.request.LoginRequest;
import com.abhi.socialMedia.dto.request.RegisterRequest;
import com.abhi.socialMedia.dto.response.AuthResponse;
import com.abhi.socialMedia.dto.response.UserResponse;

public interface UserService{
    UserResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
