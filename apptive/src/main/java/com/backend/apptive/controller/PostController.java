package com.backend.apptive.controller;

import com.backend.apptive.dto.PostDto;
import com.backend.apptive.dto.UserDto;
import com.backend.apptive.service.PostService;
import com.backend.apptive.service.UserService;
import com.backend.apptive.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiUtils.fail(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }


}
