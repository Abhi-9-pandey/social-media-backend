package com.abhi.socialMedia.dto.response;

import com.abhi.socialMedia.common.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProfileResponse(

        Long id,

        String username,

        String email,

        String firstName,

        String lastName,

        String bio,

        LocalDate dateOfBirth,

        String profilePictureUrl,

        String coverPictureUrl,

        Gender gender,

        String phoneNumber

) {}