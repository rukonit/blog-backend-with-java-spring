package com.rukon.blog.service;

import com.rukon.blog.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
}
