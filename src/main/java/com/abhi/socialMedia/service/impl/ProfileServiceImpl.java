package com.abhi.socialMedia.service.impl;

import com.abhi.socialMedia.dto.request.UpdateProfileRequest;
import com.abhi.socialMedia.dto.response.ProfileResponse;
import com.abhi.socialMedia.entity.Profile;
import com.abhi.socialMedia.entity.User;
import com.abhi.socialMedia.exception.ResourceNotFoundException;
import com.abhi.socialMedia.repository.ProfileRepository;
import com.abhi.socialMedia.repository.UserRepository;
import com.abhi.socialMedia.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }

    private ProfileResponse mapToResponse(Profile profile) {
        User user = profile.getUser();

        return ProfileResponse.builder()
                .id(profile.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .bio(profile.getBio())
                .dateOfBirth(profile.getDateOfBirth())
                .profilePictureUrl(profile.getProfilePictureUrl())
                .coverPictureUrl(profile.getCoverPictureUrl())
                .gender(profile.getGender())
                .phoneNumber(profile.getPhoneNumber())
                .build();
    }

    @Override
    public ProfileResponse getMyProfile() {
        User user = getCurrentUser();

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found."));

        return mapToResponse(profile);
    }

    @Override
    public ProfileResponse updateMyProfile(UpdateProfileRequest request) {
        User user = getCurrentUser();

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found."));

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setBio(request.getBio());
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setProfilePictureUrl(request.getProfilePictureUrl());
        profile.setCoverPictureUrl(request.getCoverPictureUrl());
        profile.setGender(request.getGender());
        profile.setPhoneNumber(request.getPhoneNumber());
        Profile savedProfile = profileRepository.save(profile);

        return mapToResponse(savedProfile);
    }

    @Override
    public ProfileResponse getProfileByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found."));

        return mapToResponse(profile);
    }

}
