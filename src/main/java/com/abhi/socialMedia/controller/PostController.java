package com.abhi.socialMedia.controller;

import com.abhi.socialMedia.dto.request.CreatePostRequest;
import com.abhi.socialMedia.dto.response.PostResponse;
import com.abhi.socialMedia.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(
            @Valid @RequestBody CreatePostRequest request
            ) {
        return postService.createPost(request);
    }

    @GetMapping("/me")
    public List<PostResponse> getMyPosts() {
        return postService.getMyPosts();
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }
}
