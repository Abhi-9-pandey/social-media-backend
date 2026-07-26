package com.abhi.socialMedia.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequest {

    @NotNull
    private Long userId;
}
