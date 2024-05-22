package com.backend.apptive.controller;

import com.backend.apptive.dto.PostDto;
import com.backend.apptive.dto.UserDto;
import com.backend.apptive.service.PostService;
import com.backend.apptive.service.UserService;
import com.backend.apptive.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto.Request request) {
        try {
            postService.create(request);
            return ResponseEntity.ok(ApiUtils.success("게시물이 생성되었습니다."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiUtils.fail(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiSuccess<List<PostDto.Response>>> findAllPosts() {
        List<PostDto.Response> posts = postService.findAll();
        return ResponseEntity.ok()
                .body(ApiUtils.success(posts));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiUtils.ApiSuccess<PostDto.DetailResponse>> findPost(@PathVariable Long postId) {
        PostDto.DetailResponse post = postService.findByPostId(postId);
        return ResponseEntity.ok()
                .body(ApiUtils.success(post));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<List<PostDto.Response>>> findUserPosts(@PathVariable String email) {
        List<PostDto.Response> posts = postService.findByUserEmail(email);
        return ResponseEntity.ok()
                .body(ApiUtils.success(posts));
    }
}
