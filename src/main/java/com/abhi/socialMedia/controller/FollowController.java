package com.abhi.socialMedia.controller;

import com.abhi.socialMedia.dto.request.FollowRequest;
import com.abhi.socialMedia.dto.response.FollowResponse;
import com.abhi.socialMedia.service.FollowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public FollowResponse followUser(@Valid @RequestBody FollowRequest request) {
        return followService.followUser(request);
    }

    @DeleteMapping("/{userId}")
    public FollowResponse unfollowUser(@PathVariable Long userId) {
        return followService.unfollowUser(userId);
    }

    @GetMapping("/{userId}")
    public FollowResponse getFollowStatus(@PathVariable Long userId) {
        return followService.getFollowStatus(userId);
    }
}
