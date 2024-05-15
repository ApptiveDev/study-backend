package com.backend.apptive.dto;

import com.backend.apptive.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Builder
@Getter
public class UserDto {

    private String name;
    private String email;


    public static UserDto toDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Builder
    @Getter
    public static class DtoList {
        List<UserDto> userDtos;

        public static UserDto.DtoList toDto(List<User> users) {
            List<UserDto> userDtos = users.stream()
                    .map(UserDto::toDto)
                    .collect(Collectors.toList());

            return UserDto.DtoList.builder()
                    .userDtos(userDtos)
                    .build();
        }
    }
}
