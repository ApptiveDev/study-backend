package com.backend.apptive.dto;

import com.backend.apptive.domain.Post;
import com.backend.apptive.domain.User;
import com.backend.apptive.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public class PostDto {
    @Builder
    @Getter
    public static class Request{
        private String userEmail;
        private String title;
        private String content;

        public Post toEntity(UserRepository userRepository) {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));

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
        private Long postId;

        public static PostDto.Response toDto(Post post) {
        return Response.builder()
                .title(post.getTitle())
                .postId(post.getPostId())
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
