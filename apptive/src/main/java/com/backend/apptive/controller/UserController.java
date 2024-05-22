package com.backend.apptive.controller;

import com.backend.apptive.dto.UserDto;
import com.backend.apptive.service.UserService;

import com.backend.apptive.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDto.Request request) {
        try {
            userService.save(request);
            return ResponseEntity.ok(ApiUtils.success("유저가 생성되었습니다."));
        } catch (RuntimeException e) { // 이메일 중복 처리 추가
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiUtils.fail(HttpStatus.CONFLICT.value(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiSuccess<List<UserDto.Response>>> findAllUser() {
        List<UserDto.Response> users = userService.findAll();
        return ResponseEntity.ok()
                .body(ApiUtils.success(users));
    }

    @GetMapping("/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<UserDto.Response>> findUser(@PathVariable String email) {
        UserDto.Response user = userService.findByEmail(email);
        return ResponseEntity.ok(ApiUtils.success(user));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<String>> deleteUser(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok(ApiUtils.success("유저가 삭제되었습니다."));
    }

    @PutMapping("/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<String>> updateUser(@PathVariable String email, @RequestBody UserDto.Request request) {
        userService.update(email, request);
        return ResponseEntity.ok(ApiUtils.success("이름이 변경되었습니다."));
    }
}
