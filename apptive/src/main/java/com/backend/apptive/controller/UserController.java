package com.backend.apptive.controller;

import com.backend.apptive.dto.AddUserRequest;
import com.backend.apptive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.apptive.domain.User;

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
}
