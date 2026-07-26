package com.abhi.socialMedia.service.impl;

import com.abhi.socialMedia.common.enums.Role;
import com.abhi.socialMedia.dto.request.LoginRequest;
import com.abhi.socialMedia.dto.request.RegisterRequest;
import com.abhi.socialMedia.dto.response.AuthResponse;
import com.abhi.socialMedia.dto.response.UserResponse;
import com.abhi.socialMedia.entity.Profile;
import com.abhi.socialMedia.entity.User;
import com.abhi.socialMedia.exception.AuthenticationFailedException;
import com.abhi.socialMedia.exception.ResourceAlreadyExistsException;
import com.abhi.socialMedia.repository.ProfileRepository;
import com.abhi.socialMedia.repository.UserRepository;
import com.abhi.socialMedia.security.JwtService;
import com.abhi.socialMedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceAlreadyExistsException("Username already exists.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists.");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);

        Profile profile = Profile.builder()
                .user(savedUser)
                .build();

        profileRepository.save(profile);

        return UserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthenticationFailedException("Invalid username or password."));

        if (!user.isEnabled()) {
            throw new AuthenticationFailedException("Account is disabled.");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationFailedException("Invalid username or password.");
        }

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
