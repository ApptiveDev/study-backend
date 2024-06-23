package com.backend.apptive.dto;

import com.backend.apptive.domain.Post;
import com.backend.apptive.domain.User;
import com.backend.apptive.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.Getter;

public class PostDto {
    @Builder
    @Getter
    public static class Request{
        private String userEmail;
        private String title;
        private String content;

        // 2 - 수정함, DTO 단에는 Repository 들고오지 말자
        public Post toEntity(User user) {
            return Post.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Response {
        private String title;
        private Long id;

        public static PostDto.Response toDto(Post post) {
        return Response.builder()
                .title(post.getTitle())
                .id(post.getId())
                .build();
        }
    }

    @Builder
    @Getter
    public static class DetailResponse {
        private String title;
        private String content;

        public static PostDto.DetailResponse toDto(Post post) {
            return DetailResponse.builder()
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();
        }
    }
}
