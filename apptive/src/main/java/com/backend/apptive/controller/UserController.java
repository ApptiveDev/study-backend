package com.backend.apptive.controller;

import com.backend.apptive.dto.AddUserRequest;
import com.backend.apptive.dto.UserResponse;
import com.backend.apptive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.backend.apptive.domain.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest request) {
        User savedUser = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserResponse> user = userService.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(user);
    }

    @GetMapping("/api/users/{email}")
    public ResponseEntity<UserResponse> findUsers(@PathVariable String email) {
        User user = userService.findByEmail(email);

        return ResponseEntity.ok()
                .body(new UserResponse(user));
    }
}
