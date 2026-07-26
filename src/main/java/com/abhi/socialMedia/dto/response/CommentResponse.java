package com.abhi.socialMedia.dto.response;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;

    private Long postId;

    private String username;

    private String content;

    private LocalDateTime createdAt;
}
