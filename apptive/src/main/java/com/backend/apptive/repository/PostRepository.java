package com.backend.apptive.repository;

import com.backend.apptive.domain.Post;
import com.backend.apptive.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
    List<Post> findByUserEmail(String userEmail);
}
