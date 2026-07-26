package com.abhi.socialMedia.repository;

import com.abhi.socialMedia.entity.Profile;
import com.abhi.socialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
}
