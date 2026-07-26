package com.abhi.socialMedia.controller;

import com.abhi.socialMedia.dto.request.UpdateProfileRequest;
import com.abhi.socialMedia.dto.response.ProfileResponse;
import com.abhi.socialMedia.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile() {
        return ResponseEntity.ok(profileService.getMyProfile());
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResponse> updateMyProfile(
            @Valid @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.ok(profileService.updateMyProfile(request));
    }


    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> getProfile(
            @PathVariable String username) {

        return ResponseEntity.ok(profileService.getProfileByUsername(username));
    }
}