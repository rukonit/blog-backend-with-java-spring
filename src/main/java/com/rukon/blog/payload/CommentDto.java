package com.rukon.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Email should not empty")
    @Email
    private String email;

    @NotEmpty(message = "Message should not be empty")
    @Size(min = 10,  message = "Body must be minimum 10 character")
    private String body;
}
