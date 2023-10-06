package com.comment.service.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Description cannot be blank")
    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;

    private User userDetails;
}
