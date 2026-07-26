package com.abhi.socialMedia.service;

import com.abhi.socialMedia.dto.request.FollowRequest;
import com.abhi.socialMedia.dto.response.FollowResponse;

public interface FollowService {

    FollowResponse followUser(FollowRequest request);

    FollowResponse unfollowUser(Long userId);

    FollowResponse getFollowStatus(Long userId);
}
