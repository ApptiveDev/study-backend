package com.backend.apptive.service;


import com.backend.apptive.domain.Post;
import com.backend.apptive.domain.User;
import com.backend.apptive.dto.PostDto;
import com.backend.apptive.repository.PostRepository;
import com.backend.apptive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addPost(PostDto.Request postDto) {
        User user = userRepository.findByEmail(postDto.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));


        postRepository.save(Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .user(user)
                .build());
    }
    
    public List<PostDto.Response> gerUserPost(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getPosts().stream().map(PostDto.Response::toDto).toList();
    }

    public List<PostDto.Response> NplusOne() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .flatMap(user -> user.getPosts().stream())
                .map(PostDto.Response::toDto)
                .collect(Collectors.toList());
    }
}

// 1 + N  // (N = 3)
