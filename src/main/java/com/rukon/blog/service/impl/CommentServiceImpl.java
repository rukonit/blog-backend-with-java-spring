package com.rukon.blog.service.impl;

import com.rukon.blog.exception.BlogAPIException;
import com.rukon.blog.exception.ResourceNotFoundException;
import com.rukon.blog.model.Comment;
import com.rukon.blog.model.Post;
import com.rukon.blog.payload.CommentDto;
import com.rukon.blog.repository.CommentRepository;
import com.rukon.blog.repository.PostRepository;
import com.rukon.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        // retrive post entity
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //set post to comment entity
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapToCommentDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //retrive comment by post id
        List<CommentDto> comments = commentRepository.findByPostId(postId).stream().map(
                comment -> mapToCommentDto(comment)
        ).collect(Collectors.toList());

        return comments;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        Comment comment = verifyComment(postId, commentId);

        return mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentDto) {

        Comment comment = verifyComment(postId, commentId);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment =  commentRepository.save(comment);

        return mapToCommentDto(updatedComment);
    }

    @Override
    public void deleteCommentById(Long postId, long commentId) {

        verifyComment(postId, commentId);

        commentRepository.deleteById(commentId);
    }

    private CommentDto mapToCommentDto (Comment comment) {


        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity (CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    private Comment verifyComment(long postId, long commentId) {
        // retrive post entity
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals((post.getId()))) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return comment;
    }

}
