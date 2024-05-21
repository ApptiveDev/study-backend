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

        private UserRepository userRepository;

        public Post toEntity() {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));

            return Post.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    private UserRepository userRepository;

    /*public static PostDto toDto(Post post) {
        return PostDto.builder()
                .user(user)
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }*/

}
