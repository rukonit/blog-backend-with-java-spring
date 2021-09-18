package com.rukon.blog.controller;

import com.rukon.blog.payload.CommentDto;
import com.rukon.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for Commnet Resource")
@RestController
@RequestMapping("/api/v1/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Create comment REST API")
    @PostMapping("{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long id, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Comments by POST ID REST API")
    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(name = "postId") Long postId) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);

    }

    @ApiOperation(value = "GET single comment by id REST API")
    @GetMapping("{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId, @PathVariable(name = "id") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @ApiOperation(value = "UPDATE single comment by id REST API")
    @PutMapping("{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") long postId,
                                                    @PathVariable(name = "id") long commentId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @ApiOperation(value = "DELETE single comment by id REST API")
    @DeleteMapping("{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(name = "postId") long postId,
                                                    @PathVariable(name = "id") long commentId) {
    commentService.deleteCommentById(postId, commentId);
    return new ResponseEntity<>("Comment was deleted successfully", HttpStatus.OK);
    }


}
