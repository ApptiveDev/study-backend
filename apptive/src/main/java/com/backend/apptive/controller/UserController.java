package com.backend.apptive.controller;

import com.backend.apptive.dto.AddUserRequest;
import com.backend.apptive.dto.UpdateUserRequest;
import com.backend.apptive.dto.UserResponse;
import com.backend.apptive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/api/user")
    public ResponseEntity<List<UserResponse>> findAllUser() {
        List<UserResponse> user = userService.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(user);
    }

    @GetMapping("/api/user/{email}")
    public ResponseEntity<UserResponse> findUser(@PathVariable String email) {
        User user = userService.findByEmail(email);

        return ResponseEntity.ok()
                .body(new UserResponse(user));
    }

    @DeleteMapping("/api/user/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteByEmail(email);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/user/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email,
                                             @RequestBody UpdateUserRequest request) {
        User updateUser = userService.update(email, request);

        return ResponseEntity.ok()
                .body(updateUser);
    }
}
