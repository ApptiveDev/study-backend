package com.backend.apptive.dto;
import com.backend.apptive.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    @Builder
    @Getter
    public static class Request {
        private String name;
        private String email;

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .email(email)
                    .build();
        }
    }
    @Builder
    @Getter
    public static class Response {
        private String name;

        public static UserDto.Response toDto(User user) {
            return Response.builder()
                    .name(user.getName())
                    .build();
        }
    }
}