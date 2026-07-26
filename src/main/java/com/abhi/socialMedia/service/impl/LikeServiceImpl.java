package com.abhi.socialMedia.service.impl;

import com.abhi.socialMedia.dto.request.LikeRequest;
import com.abhi.socialMedia.dto.response.LikeResponse;
import com.abhi.socialMedia.entity.Like;
import com.abhi.socialMedia.entity.Post;
import com.abhi.socialMedia.entity.User;
import com.abhi.socialMedia.repository.LikeRepository;
import com.abhi.socialMedia.repository.PostRepository;
import com.abhi.socialMedia.repository.UserRepository;
import com.abhi.socialMedia.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public LikeResponse likePost(LikeRequest request) {

        User user = getCurrentUser();

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!likeRepository.existsByUserIdAndPostId(user.getId(), post.getId())) {

            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();

            likeRepository.save(like);
        }

        return getLikeStatus(post.getId());
    }

    @Override
    public LikeResponse unlikePost(Long postId) {

        User user = getCurrentUser();

        Like like = likeRepository.findByUserIdAndPostId(user.getId(), postId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepository.delete(like);

        return getLikeStatus(postId);
    }

    @Override
    public LikeResponse getLikeStatus(Long postId) {

        User user = getCurrentUser();

        return LikeResponse.builder()
                .postId(postId)
                .likeCount(likeRepository.countByPostId(postId))
                .liked(likeRepository.existsByUserIdAndPostId(user.getId(), postId))
                .build();
    }
}
