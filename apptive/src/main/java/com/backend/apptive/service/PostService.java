package com.backend.apptive.service;

import com.backend.apptive.domain.Post;

import com.backend.apptive.dto.PostDto;
import com.backend.apptive.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void create(PostDto.Request request) {
        postRepository.save(request.toEntity());
    }

    public List<PostDto.Response> findAll() {
        List<PostDto.Response> posts = postRepository.findAll()
                .stream()
                .map(PostDto.Response::toDto)
                .toList();
        return posts;
    }

    public PostDto.DetailResponse findByPostId(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + postId));
        return PostDto.DetailResponse.toDto(post);
    }

    public List<PostDto.Response> findByUserEmail(String userEmail) {
        List<PostDto.Response> posts = postRepository.findByUserEmail(userEmail)
                .stream()
                .map(PostDto.Response::toDto)
                .toList();
        return posts;
    }
}
