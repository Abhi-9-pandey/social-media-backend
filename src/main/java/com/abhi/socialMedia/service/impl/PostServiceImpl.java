package com.abhi.socialMedia.service.impl;

import com.abhi.socialMedia.dto.request.CreatePostRequest;
import com.abhi.socialMedia.dto.response.PostResponse;
import com.abhi.socialMedia.entity.Post;
import com.abhi.socialMedia.entity.User;
import com.abhi.socialMedia.exception.ResourceNotFoundException;
import com.abhi.socialMedia.repository.PostRepository;
import com.abhi.socialMedia.repository.UserRepository;
import com.abhi.socialMedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostResponse createPost(CreatePostRequest request) {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        Post post = Post.builder()
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .user(user)
                .build();

        Post savedPost = postRepository.save(post);

        return mapToResponse(savedPost);
    }

    @Override
    public List<PostResponse> getMyPosts() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        return postRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public List<PostResponse> getAllPosts() {

        // Fetches all posts and sorts them so the newest posts appear at the top
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PostResponse mapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .username(post.getUser().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
