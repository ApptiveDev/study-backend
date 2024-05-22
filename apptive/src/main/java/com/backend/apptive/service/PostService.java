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

    //1 서비스 단에서 레포지토리 접근 하기 - 수정 완료
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

    public PostDto.DetailResponse findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다: " + id));

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
