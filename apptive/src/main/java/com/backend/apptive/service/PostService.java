package com.backend.apptive.service;

import com.backend.apptive.domain.User;
import com.backend.apptive.dto.PostDto;
import com.backend.apptive.dto.UserDto;
import com.backend.apptive.repository.PostRepository;
import com.backend.apptive.repository.UserRepository;
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
}
