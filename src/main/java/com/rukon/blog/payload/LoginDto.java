package com.rukon.blog.payload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "Login details")
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
