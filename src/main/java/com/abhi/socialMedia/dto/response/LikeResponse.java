package com.abhi.socialMedia.dto.response;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {

    private Long postId;

    private long likeCount;

    private boolean liked;
}
