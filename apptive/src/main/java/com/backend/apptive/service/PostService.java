package com.backend.apptive.service;

import com.backend.apptive.domain.User;
import com.backend.apptive.dto.PostDto;
import com.backend.apptive.repository.PostRepository;
import com.backend.apptive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void create(PostDto request) {
        postRepository.save(request.toEntity());
    }
}
