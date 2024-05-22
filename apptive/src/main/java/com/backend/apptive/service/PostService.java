package com.backend.apptive.service;

import com.backend.apptive.domain.Post;

import com.backend.apptive.domain.User;
import com.backend.apptive.dto.PostDto;
import com.backend.apptive.repository.PostRepository;
import com.backend.apptive.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // 생성자 주입
@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //1 - 수정 완료
    @Transactional
    public void create(PostDto.Request request) {
        User user = userRepository.findByEmail(request.getUserEmail())
                .orElseThrow(() -> new
                        EntityNotFoundException("유저를 찾을 수 없습니다: " + request.getUserEmail()));
        postRepository.save(request.toEntity(user));
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
                .orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다: " + postId));

        return PostDto.DetailResponse.toDto(post);
    }

    public List<PostDto.Response> findByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다: " + userEmail));

        return postRepository.findByUserEmail(user.getEmail())
                .stream()
                .map(PostDto.Response::toDto)
                .toList();
    }
}
