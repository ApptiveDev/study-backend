package com.backend.apptive.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //updateName 같은 이름사용
    public void update(String name) {
        this.name = name;
    }
}
