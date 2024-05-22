package com.backend.apptive.dto;

import com.backend.apptive.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class PostDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String title;
        private String content;
        private String userEmail;
    }
    @Getter
    @Builder
    public static class Response {
        private String title;
        private Long postId;

        public static PostDto.Response toDto(Post post) {
            return Response.builder()
                    .title(post.getTitle())
                    .postId(post.getId())
                    .build();
        }
    }
}
