package com.backend.apptive.repository;

import com.backend.apptive.domain.Post;
import com.backend.apptive.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p join fetch p.user")
    List<Post> findAllJoinFetch();
}
