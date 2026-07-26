package com.abhi.socialMedia.dto.response;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowResponse {

    private Long userId;

    private long followers;

    private long following;

    private boolean followingUser;
}
