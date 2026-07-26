package com.abhi.socialMedia.service;

import com.abhi.socialMedia.dto.request.CreateCommentRequest;
import com.abhi.socialMedia.dto.request.UpdateCommentRequest;
import com.abhi.socialMedia.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CreateCommentRequest request);

    CommentResponse updateComment(Long commentId,
                                  UpdateCommentRequest request);

    List<CommentResponse> getCommentsByPost(Long postId);

    void deleteComment(Long commentId);
}
