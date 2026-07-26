package com.abhi.socialMedia.service.impl;

import com.abhi.socialMedia.dto.request.CreateCommentRequest;
import com.abhi.socialMedia.dto.request.UpdateCommentRequest;
import com.abhi.socialMedia.dto.response.CommentResponse;
import com.abhi.socialMedia.entity.Comment;
import com.abhi.socialMedia.entity.Post;
import com.abhi.socialMedia.entity.User;
import com.abhi.socialMedia.repository.CommentRepository;
import com.abhi.socialMedia.repository.PostRepository;
import com.abhi.socialMedia.repository.UserRepository;
import com.abhi.socialMedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponse createComment(CreateCommentRequest request) {

        Authentication authentication =
                org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .build();

        comment = commentRepository.save(comment);

        return map(comment);
    }

    @Override
    public CommentResponse updateComment(Long commentId,
                                         UpdateCommentRequest request) {

        Authentication authentication =
                org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can edit only your own comments");
        }

        comment.setContent(request.getContent());

        comment = commentRepository.save(comment);

        return map(comment);
    }

    @Override
    public void deleteComment(Long commentId) {

        Authentication authentication =
                org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can delete only your own comments");
        }

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentResponse> getCommentsByPost(Long postId) {

        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId)
                .stream()
                .map(this::map)
                .toList();
    }

    private CommentResponse map(Comment comment) {

        return CommentResponse.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .username(comment.getUser().getUsername())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}