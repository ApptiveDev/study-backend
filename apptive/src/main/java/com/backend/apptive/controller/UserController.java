package com.backend.apptive.controller;

import com.backend.apptive.dto.UserDto;
import com.backend.apptive.service.UserService;

import com.backend.apptive.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiUtils.ApiSuccess<String>> addUser(@RequestBody UserDto request) {
        userService.save(request);
        return ResponseEntity.ok(ApiUtils.success("유저가 생성되었습니다."));
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiSuccess<List<UserDto>>> findAllUser() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok()
                .body(ApiUtils.success(users));
    }

    @GetMapping("/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<UserDto>> findUser(@PathVariable String email) {
        UserDto user = userService.findByEmail(email);
        return ResponseEntity.ok(ApiUtils.success(user));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<String>> deleteUser(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok(ApiUtils.success("유저가 삭제되었습니다."));
    }

    @PutMapping("/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<String>> updateUser(@PathVariable String email,
                                             @RequestBody UserDto request) {
        UserDto user= userService.update(email, request);
        return ResponseEntity.ok(ApiUtils.success("이름이 변경되었습니다."));
    }
}
