package com.abhi.socialMedia.repository;

import com.abhi.socialMedia.entity.Post;
import com.abhi.socialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUserOrderByCreatedAtDesc(User user);
}
