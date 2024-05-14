package com.backend.apptive.dto;

import lombok.Getter;
import com.backend.apptive.domain.User;

@Getter
public class UserResponse {
    private final String name;

    public UserResponse(User users){
        this.name = users.getName();
    }
}
