package com.abhi.socialMedia.service;

import com.abhi.socialMedia.dto.request.UpdateProfileRequest;
import com.abhi.socialMedia.dto.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse getMyProfile();

    ProfileResponse updateMyProfile(UpdateProfileRequest request);

    ProfileResponse getProfileByUsername(String username);
}