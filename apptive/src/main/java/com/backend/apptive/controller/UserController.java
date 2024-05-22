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
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody UserDto.Request request) {
        userService.save(request);
        return ResponseEntity.ok(ApiUtils.success("유저가 생성되었습니다."));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiUtils.ApiSuccess<List<UserDto.Response>>> findAllUser() {
        return ResponseEntity.ok()
                .body(ApiUtils.success(userService.findAll()));
    }

    @GetMapping("user/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<UserDto.Response>> findUser(@PathVariable String email) {
        return ResponseEntity.ok(ApiUtils.success(userService.findByEmail(email)));
    }

    @DeleteMapping("user/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<String>> deleteUser(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok(ApiUtils.success("유저가 삭제되었습니다."));
    }

    @PatchMapping("user/{email}")
    public ResponseEntity<ApiUtils.ApiSuccess<String>> updateUser(@PathVariable String email, @RequestBody UserDto.Request request) {
        userService.update(email, request);
        return ResponseEntity.ok(ApiUtils.success("이름이 변경되었습니다."));
    }
}
