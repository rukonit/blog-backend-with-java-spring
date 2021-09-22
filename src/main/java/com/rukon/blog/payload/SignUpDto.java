package com.rukon.blog.payload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "Sign up details")
@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
