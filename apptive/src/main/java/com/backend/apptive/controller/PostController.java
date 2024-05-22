package com.backend.apptive.controller;

import com.backend.apptive.dto.PostDto;
import com.backend.apptive.service.PostService;
import com.backend.apptive.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<ApiUtils.ApiSuccess<String>> createPost(@RequestBody PostDto.Request request) {
        postService.create(request);
        return ResponseEntity.ok(ApiUtils.success("게시물이 생성되었습니다."));
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiUtils.ApiSuccess<List<PostDto.Response>>> findAllPosts() {
        return ResponseEntity.ok()
                .body(ApiUtils.success(postService.findAll()));
    }

    @GetMapping("post/{id}")
    public ResponseEntity<ApiUtils.ApiSuccess<PostDto.DetailResponse>> findPost(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(ApiUtils.success(postService.findById(id)));
    }

    @GetMapping("posts/users/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<List<PostDto.Response>>> findUserPosts(@PathVariable String email) {
        return ResponseEntity.ok()
                .body(ApiUtils.success(postService.findByUserEmail(email)));
    }
}
