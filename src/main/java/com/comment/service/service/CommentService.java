package com.comment.service.service;

import com.comment.service.payload.CommentDto;

public interface CommentService {

    CommentDto saveComment(CommentDto commentDto);
}
