package com.backend.apptive.service;

import com.backend.apptive.dto.PostDto;

import java.util.List;

public interface PostService {
    void create(PostDto.Request request);
    List<PostDto.Response> findAll();
    PostDto.DetailResponse findById(Long id);
    List<PostDto.Response> findByUserEmail(String email);
}
