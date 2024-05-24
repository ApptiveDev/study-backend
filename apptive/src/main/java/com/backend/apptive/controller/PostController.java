package com.backend.apptive.controller;


import com.backend.apptive.dto.PostDto;
import com.backend.apptive.dto.UserDto;
import com.backend.apptive.service.PostService;
import com.backend.apptive.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiUtils.ApiSuccess<String>> addPost(@RequestBody PostDto.Request postDto) {
        postService.addPost(postDto);
        return ResponseEntity.ok(ApiUtils.success("포스터가 등록되었습니다."));
    }

    @GetMapping
    public ResponseEntity<List<PostDto.Response>> getPost(@RequestParam String email) {
        return ResponseEntity.ok(postService.gerUserPost(email));
    }

    @GetMapping("/n-plus-one")
    public ResponseEntity<?> getAllPostSize() {
        return ResponseEntity.ok(postService.NplusOne());
    }
}
