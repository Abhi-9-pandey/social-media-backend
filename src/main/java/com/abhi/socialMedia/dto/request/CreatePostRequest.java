package com.abhi.socialMedia.dto.request;
import com.abhi.socialMedia.common.enums.PostVisibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {

    @NotBlank(message = "Content cannot be empty.")
    @Size(max = 3000, message = "Post cannot exceed 2000 characters.")
    private String content;

    private String imageUrl;

    private PostVisibility visibility = PostVisibility.PUBLIC;
}
