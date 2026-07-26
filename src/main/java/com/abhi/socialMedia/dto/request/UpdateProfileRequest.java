package com.abhi.socialMedia.dto.request;

import com.abhi.socialMedia.common.enums.Gender;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateProfileRequest {

    private String firstName;

    private String lastName;

    private String bio;

    @Past(message = "Date of birth must be in the past.")
    private LocalDate dateOfBirth;

    private String profilePictureUrl;

    private String coverPictureUrl;

    private Gender gender;

    private String phoneNumber;
}