package com.abhi.socialMedia.dto.response;

import com.abhi.socialMedia.common.enums.PostVisibility;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {
    private Long id;

    private String content;

    private String imageUrl;

    private String username;

    private PostVisibility visibility;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
