package com.backend.apptive.DTO;

import com.backend.apptive.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;

    public static UserDTO fromEntity(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
    public User toEntity(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .id(this.id)
                .build();
    }
}
