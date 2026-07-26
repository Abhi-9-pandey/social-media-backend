package com.abhi.socialMedia.controller;

import com.abhi.socialMedia.dto.request.CreateCommentRequest;
import com.abhi.socialMedia.dto.request.UpdateCommentRequest;
import com.abhi.socialMedia.dto.response.CommentResponse;
import com.abhi.socialMedia.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentResponse createComment(
            @Valid @RequestBody CreateCommentRequest request) {

        return commentService.createComment(request);
    }

    @PutMapping("/{id}")
    public CommentResponse updateComment(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentRequest request) {

        return commentService.updateComment(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponse> getComments(
            @PathVariable Long postId) {

        return commentService.getCommentsByPost(postId);
    }
}