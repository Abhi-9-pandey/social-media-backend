package com.abhi.socialMedia.service;

import com.abhi.socialMedia.dto.request.CreatePostRequest;
import com.abhi.socialMedia.dto.response.PostResponse;

import java.util.List;

public interface PostService {

    PostResponse createPost(CreatePostRequest request);

    List<PostResponse> getMyPosts();

    List<PostResponse> getAllPosts();
}
