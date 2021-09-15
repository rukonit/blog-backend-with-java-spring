package com.rukon.blog.service;

import com.rukon.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(Long postId, long commentId, CommentDto commentDto);

    void deleteCommentById(Long postId, long commentId);
}
