package com.backend.apptive.dto;
import com.backend.apptive.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddUserRequest {
    private String name;
    private String email;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }

}
