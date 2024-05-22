package com.backend.apptive.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // 지연 로딩 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 3 JoinColumn 변경 완료
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    // 연관관계 매핑 편의 메소드 **
    public void makeRelation(User user){
        this.user = user;
        user.getPostList().add(this);
    }
}
