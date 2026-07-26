package com.abhi.socialMedia.controller;


import com.abhi.socialMedia.dto.request.LikeRequest;
import com.abhi.socialMedia.dto.response.LikeResponse;
import com.abhi.socialMedia.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public LikeResponse likePost(@Valid @RequestBody LikeRequest request) {
        return likeService.likePost(request);
    }

    @DeleteMapping("/{postId}")
    public LikeResponse unlikePost(@PathVariable Long postId) {
        return likeService.unlikePost(postId);
    }

    @GetMapping("/{postId}")
    public LikeResponse getLikeStatus(@PathVariable Long postId) {
        return likeService.getLikeStatus(postId);
    }
}
