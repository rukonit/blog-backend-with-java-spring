package com.rukon.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Comment id")
    private long id;

    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "Email should not empty")
    @Email
    private String email;


    @ApiModelProperty(value = "Comment body")
    @NotEmpty(message = "Message should not be empty")
    @Size(min = 10,  message = "Body must be minimum 10 character")
    private String body;
}
