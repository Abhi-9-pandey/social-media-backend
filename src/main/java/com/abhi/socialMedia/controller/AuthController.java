package com.abhi.socialMedia.controller;

import com.abhi.socialMedia.dto.request.LoginRequest;
import com.abhi.socialMedia.dto.request.RegisterRequest;
import com.abhi.socialMedia.dto.response.AuthResponse;
import com.abhi.socialMedia.dto.response.UserResponse;
import com.abhi.socialMedia.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request
            ){
        UserResponse response = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        System.out.println(">>>>>inside login controler<<<<<<");
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
