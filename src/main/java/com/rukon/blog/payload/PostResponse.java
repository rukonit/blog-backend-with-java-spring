package com.rukon.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description = "Post model response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    @ApiModelProperty(value = "List of content")
    private List<PostDto> content;
    @ApiModelProperty(value = "Current page number")
    private int pageNo;
    @ApiModelProperty(value = "Content size")
    private int pageSize;
    @ApiModelProperty(value = "Total elements")
    private long totalElements;
    @ApiModelProperty(value = "Total pages")
    private int totalPages;
    @ApiModelProperty(value = "true or false if this is the last page")
    private boolean last;
}
