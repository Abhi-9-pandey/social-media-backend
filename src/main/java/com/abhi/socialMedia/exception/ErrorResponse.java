package com.abhi.socialMedia.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(

        LocalDateTime timestamp,
        int status,
        String message

) {
}
