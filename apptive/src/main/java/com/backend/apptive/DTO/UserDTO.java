package com.backend.apptive.DTO;

import com.backend.apptive.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String email;
    private String name;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .build();
    }

    public static UserDTO toDto(User user){
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
