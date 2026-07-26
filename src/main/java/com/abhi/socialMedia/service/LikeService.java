package com.abhi.socialMedia.service;

import com.abhi.socialMedia.dto.request.LikeRequest;
import com.abhi.socialMedia.dto.response.LikeResponse;

public interface LikeService {

    LikeResponse likePost(LikeRequest request);

    LikeResponse unlikePost(Long postId);

    LikeResponse getLikeStatus(Long postId);
}
