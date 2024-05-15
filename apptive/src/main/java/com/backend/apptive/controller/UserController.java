package com.backend.apptive.controller;

import com.backend.apptive.dto.AddUserRequest;
import com.backend.apptive.dto.UpdateUserRequest;
import com.backend.apptive.dto.UserResponse;
import com.backend.apptive.service.UserService;
import com.backend.apptive.utils.ApiUtils;
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
 @RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    //공통된 api url 위로 빼기
    @PostMapping("/api/user")
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest request) {
        // entity 주의
        User savedUser = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @GetMapping("/api/user")
    public ResponseEntity<List<UserResponse>> findAllUser() {
        // 서비스에서 처리하는 게 나을 수도?
        List<UserResponse> user = userService.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(user);
    }

    @GetMapping("/api/user/{email}")
    public ResponseEntity<?> findUser(@PathVariable String email) {
        // entity가 controller에 있는 건 위험
        User user = userService.findByEmail(email);

        // ApiUtils 사용
        return ResponseEntity.ok()
                .body(ApiUtils.success(new UserResponse(user)));
    }

    @DeleteMapping("/api/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok()
                .build();
        // 다음과 같이 변경 성공했을 때 프론트에게 메시지 전달
//        return ResponseEntity.ok(ApiUtils.success("유저가 삭제되었습니다."));
    }

    @PutMapping("/api/user/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email,
                                             @RequestBody UpdateUserRequest request) {

        // Entity 반환x Dto로 변환해서 반환
        User updateUser = userService.update(email, request);

        // 여기도 deletUser와 같이 "이름이 변경되었습니다" 로
        return ResponseEntity.ok()
                .body(updateUser);
    }
}
