package com.abhi.socialMedia.service.impl;

import com.abhi.socialMedia.dto.request.FollowRequest;
import com.abhi.socialMedia.dto.response.FollowResponse;
import com.abhi.socialMedia.entity.Follow;
import com.abhi.socialMedia.entity.User;
import com.abhi.socialMedia.repository.FollowRepository;
import com.abhi.socialMedia.repository.UserRepository;
import com.abhi.socialMedia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public FollowResponse followUser(FollowRequest request) {

        User follower = getCurrentUser();

        User following = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (follower.getId().equals(following.getId())) {
            throw new RuntimeException("You cannot follow yourself.");
        }

        if (!followRepository.existsByFollowerIdAndFollowingId(
                follower.getId(), following.getId())) {

            Follow follow = Follow.builder()
                    .follower(follower)
                    .following(following)
                    .build();

            followRepository.save(follow);
        }

        return getFollowStatus(following.getId());
    }

    @Override
    public FollowResponse unfollowUser(Long userId) {

        User follower = getCurrentUser();

        Follow follow = followRepository
                .findByFollowerIdAndFollowingId(follower.getId(), userId)
                .orElseThrow(() -> new RuntimeException("Follow not found"));

        followRepository.delete(follow);

        return getFollowStatus(userId);
    }

    @Override
    public FollowResponse getFollowStatus(Long userId) {

        User currentUser = getCurrentUser();

        return FollowResponse.builder()
                .userId(userId)
                .followers(followRepository.countByFollowingId(userId))
                .following(followRepository.countByFollowerId(userId))
                .followingUser(
                        followRepository.existsByFollowerIdAndFollowingId(
                                currentUser.getId(), userId))
                .build();
    }
}
